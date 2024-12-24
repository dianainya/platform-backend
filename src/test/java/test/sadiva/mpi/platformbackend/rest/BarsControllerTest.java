package test.sadiva.mpi.platformbackend.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import jooq.sadiva.mpi.platformbackend.tables.pojos.Prisoner;
import jooq.sadiva.mpi.platformbackend.tables.pojos.PrisonerRating;
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
import sadiva.mpi.platformbackend.dto.admin.bars.BarsAddScoreReq;
import sadiva.mpi.platformbackend.dto.admin.bars.BarsSubtractScoreReq;
import test.sadiva.mpi.platformbackend.utils.BaseUtils;
import test.sadiva.mpi.platformbackend.utils.DishUtils;
import test.sadiva.mpi.platformbackend.utils.PrisonerUtils;
import test.sadiva.mpi.platformbackend.utils.ProductUtils;

import java.math.BigDecimal;
import java.util.UUID;

import static jooq.sadiva.mpi.platformbackend.Tables.PRISONER_RATING;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static test.sadiva.mpi.platformbackend.constants.ApiConstants.BARS_API;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = {PlatformBackendApplication.class})
@AutoConfigureMockMvc
@RequiredArgsConstructor
public class BarsControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private DSLContext dslContext;

    @Autowired
    private ObjectMapper objectMapper;

    private BaseUtils baseUtils;

    private final static String ADD_PATH = "/add";
    private final static String SUBTRACT_PATH = "/subtract";


    @PostConstruct
    public void init() {
        baseUtils = new BaseUtils(dslContext, new DishUtils(dslContext), new ProductUtils(dslContext), new PrisonerUtils(dslContext));
    }

    @BeforeEach
    public void clearData() {
        baseUtils.clearData();
    }

    @Test
    public void contextLoads() {
        assertNotNull(mockMvc);
        assertNotNull(dslContext);
        assertNotNull(objectMapper);
    }

    @Test
    public void whenAddPointsToPrisoner_thenOk() throws Exception {
        //given
        Prisoner prisoner1 = baseUtils.createPrisoner();
        Prisoner prisoner2 = baseUtils.createPrisoner();
        Prisoner prisoner3 = baseUtils.createPrisoner();

        final BarsAddScoreReq req = new BarsAddScoreReq(prisoner1.getId(), 5);

        //when
        mockMvc.perform(MockMvcRequestBuilders.post(BARS_API + ADD_PATH)
                        .content(objectMapper.writeValueAsString(req))
                        .contentType(MediaType.APPLICATION_JSON))
                //then
                .andExpect(status().isNoContent());
        PrisonerRating actualRating1 = getPrisonerRatingById(prisoner1.getId());
        PrisonerRating actualRating2 = getPrisonerRatingById(prisoner2.getId());
        PrisonerRating actualRating3 = getPrisonerRatingById(prisoner3.getId());

        assertEquals(new BigDecimal(105), actualRating1.getScore());
        assertEquals(new BigDecimal(100), actualRating2.getScore());
        assertEquals(new BigDecimal(100), actualRating3.getScore());
    }

    @Test
    public void whenSubtractPointsFromPrisoner_thenOk() throws Exception {
        //given
        Prisoner prisoner1 = baseUtils.createPrisoner();
        Prisoner prisoner2 = baseUtils.createPrisoner();
        Prisoner prisoner3 = baseUtils.createPrisoner();

        final BarsSubtractScoreReq req = new BarsSubtractScoreReq(prisoner1.getId(), "MURDER");

        //when
        mockMvc.perform(MockMvcRequestBuilders.post(BARS_API + SUBTRACT_PATH)
                        .content(objectMapper.writeValueAsString(req))
                        .contentType(MediaType.APPLICATION_JSON))
                //then
                .andExpect(status().isNoContent());
        PrisonerRating actualRating1 = getPrisonerRatingById(prisoner1.getId());
        PrisonerRating actualRating2 = getPrisonerRatingById(prisoner2.getId());
        PrisonerRating actualRating3 = getPrisonerRatingById(prisoner3.getId());

        assertEquals(new BigDecimal(50), actualRating1.getScore());
        assertEquals(new BigDecimal(100), actualRating2.getScore());
        assertEquals(new BigDecimal(100), actualRating3.getScore());
    }

    @Test
    public void whenAddPointsToNonexistentPrisoner_thenNotFound() throws Exception {
        //given
        Prisoner prisoner1 = baseUtils.createPrisoner();
        final BarsAddScoreReq req = new BarsAddScoreReq(UUID.randomUUID(), 5);

        //when
        mockMvc.perform(MockMvcRequestBuilders.post(BARS_API + ADD_PATH)
                        .content(objectMapper.writeValueAsString(req))
                        .contentType(MediaType.APPLICATION_JSON))
                //then
                .andExpect(status().isNotFound());
    }

    private PrisonerRating getPrisonerRatingById(UUID prisonerId) {
        return dslContext.select()
                .from(PRISONER_RATING)
                .where(PRISONER_RATING.PRISONER_ID.eq(prisonerId))
                .fetchOneInto(PrisonerRating.class);
    }
    @Test
    public void whenSubtractPointsFromNonexistentPrisoner_thenNotFound() throws Exception {
        // given
        final BarsSubtractScoreReq req = new BarsSubtractScoreReq(UUID.randomUUID(), "THEFT");

        // when
        mockMvc.perform(MockMvcRequestBuilders.post(BARS_API + SUBTRACT_PATH)
                        .content(objectMapper.writeValueAsString(req))
                        .contentType(MediaType.APPLICATION_JSON))
                // then
                .andExpect(status().isNotFound());
    }

    @Test
    public void whenAddNegativePoints_thenBadRequest() throws Exception {
        // given
        Prisoner prisoner = baseUtils.createPrisoner();
        final BarsAddScoreReq req = new BarsAddScoreReq(prisoner.getId(), -5);

        // when
        mockMvc.perform(MockMvcRequestBuilders.post(BARS_API + ADD_PATH)
                        .content(objectMapper.writeValueAsString(req))
                        .contentType(MediaType.APPLICATION_JSON))
                // then
                .andExpect(status().isBadRequest());
    }

}
