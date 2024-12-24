package test.sadiva.mpi.platformbackend.rest;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import jooq.sadiva.mpi.platformbackend.tables.pojos.Prisoner;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import sadiva.mpi.platformbackend.PlatformBackendApplication;
import sadiva.mpi.platformbackend.dto.admin.bars.BarsSubtractScoreReq;
import sadiva.mpi.platformbackend.dto.admin.bars.ViolationRes;
import sadiva.mpi.platformbackend.dto.admin.prisoner.PrisonerRes;
import test.sadiva.mpi.platformbackend.utils.BaseUtils;
import test.sadiva.mpi.platformbackend.utils.DishUtils;
import test.sadiva.mpi.platformbackend.utils.PrisonerUtils;
import test.sadiva.mpi.platformbackend.utils.ProductUtils;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static test.sadiva.mpi.platformbackend.constants.ApiConstants.BARS_API;
import static test.sadiva.mpi.platformbackend.constants.ApiConstants.PRISONERS_API;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {PlatformBackendApplication.class})
@AutoConfigureMockMvc
@RequiredArgsConstructor
public class InmatePointsManagementTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private DSLContext dslContext;

    @Autowired
    private ObjectMapper objectMapper;

    private BaseUtils baseUtils;

    private static final String SUBTRACT_PATH = "/subtract";
    private static final String VIOLATIONS_PATH = "/violations";

    @PostConstruct
    public void init() {
        baseUtils = new BaseUtils(dslContext, new DishUtils(dslContext), new ProductUtils(dslContext), new PrisonerUtils(dslContext));

    }

    @BeforeEach
    public void clearData() {
        baseUtils.clearData();
    }

    @Test
    public void whenAnalystSubmitsActionForm_thenPrisonerRatingIsUpdated_andAdminCanView() throws Exception {
        // Получение списка нарушений
        final List<ViolationRes> violations = callGetEndpoint(BARS_API + VIOLATIONS_PATH, new TypeReference<>() {
        });

        assertNotNull(violations, "Список нарушений не должен быть пустым");
        assertNotNull(violations.get(0).code(), "Код нарушения должен быть задан");
        assertNotNull(violations.get(0).score(), "Очки нарушения должны быть заданы");

        final ViolationRes violation = violations.get(0);
        final Prisoner prisoner = baseUtils.createPrisoner();

        // Аналитик отправляет форму с нарушением
        BarsSubtractScoreReq violationRequest = new BarsSubtractScoreReq(prisoner.getId(), violation.code());
        callPostEndpoint204(violationRequest, BARS_API + SUBTRACT_PATH);

        // Проверка обновленного рейтинга заключенного
        final PrisonerRes actualPrisoner = callGetEndpoint( PRISONERS_API + "/" + prisoner.getId(), new TypeReference<>() {
        });
        final BigDecimal expectedRating = new BigDecimal(100 - violation.score());

        assertNotNull(actualPrisoner, "Заключенный должен быть доступен.");
        assertEquals(actualPrisoner.rating(), expectedRating);
    }

    private void callPostEndpoint204(Object req, String path) throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post(path)
                        .content(objectMapper.writeValueAsString(req))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    private <T> T callGetEndpoint(String path, TypeReference<T> typeReference) throws Exception {
        return objectMapper.readValue(
                mockMvc.perform(MockMvcRequestBuilders.get(path).contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk())
                        .andReturn()
                        .getResponse().getContentAsString(),
                typeReference
        );
    }
}
