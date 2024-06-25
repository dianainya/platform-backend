package sadiva.mpi.platformbackend.controller.rest;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import sadiva.mpi.platformbackend.dto.PageResponseDto;
import sadiva.mpi.platformbackend.dto.platform.PlatformStructureRes;
import sadiva.mpi.platformbackend.service.PlatformService;

@RestController
@RequestMapping("api/v1/platform")
@RequiredArgsConstructor
//@PreAuthorize("hasAnyAuthority('admin, prisoner_register')")
public class PlatformController {
    private final PlatformService platformService;

    @PostMapping("/distribute")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(description = "Распределить заключенных по этажам согласно БАРС")
    public void distributePrisoners() {
        platformService.distributePrisoners();
    }

    @GetMapping
    @Operation(description = "Получение структуры платформы")
    public PageResponseDto<PlatformStructureRes> getPlatformStructure(@ParameterObject @PageableDefault(page = 1) Pageable pageable) {
        return platformService.getPlatformStructure(pageable);
    }
}
