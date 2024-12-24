package sadiva.mpi.platformbackend.controller.rest.admin;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import sadiva.mpi.platformbackend.dto.admin.bars.BarsAddScoreReq;
import sadiva.mpi.platformbackend.dto.admin.bars.BarsSubtractScoreReq;
import sadiva.mpi.platformbackend.dto.admin.bars.ViolationRes;
import sadiva.mpi.platformbackend.service.BarsService;

import java.util.List;

@RestController
@RequestMapping("api/v1/private/bars")
@RequiredArgsConstructor
@PreAuthorize("hasAnyAuthority('admin, prisoner_register')")
public class BarsController {
    private final BarsService barsService;

    @GetMapping("/violations")
    @Operation(description = "Получение возможных нарушений правил заключенным")
    public List<ViolationRes> getAllViolations() {
        return barsService.getAllViolations();
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(description = "Начислить баллы заключенному")
    public void addScore(@RequestBody @Valid BarsAddScoreReq req) {
        barsService.addScore(req);
    }

    @PostMapping("/subtract")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(description = "Начислить баллы заключенному")
    public void subtractScore(@RequestBody @Valid BarsSubtractScoreReq req) {
        barsService.subtractScore(req);
    }
}
