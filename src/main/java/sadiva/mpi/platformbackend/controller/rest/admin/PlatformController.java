package sadiva.mpi.platformbackend.controller.rest.admin;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import sadiva.mpi.platformbackend.dto.PageResponseDto;
import sadiva.mpi.platformbackend.dto.platform.PlatformActiveFloorRes;
import sadiva.mpi.platformbackend.dto.platform.PlatformDistribAvailabilityRes;
import sadiva.mpi.platformbackend.dto.platform.PlatformStructureRes;
import sadiva.mpi.platformbackend.service.PlatformService;

@RestController
@RequestMapping("api/v1/private/platform")
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

    @GetMapping("/distribute-availibility")
    @Operation(description = "Проверка доступности распределения заключенных по этажам согласно БАРС")
    public PlatformDistribAvailabilityRes getDistributeAvailability() {
       return platformService.getDistributeAvailability();
    }

    @GetMapping
    @Operation(description = "Получение структуры платформы")
    public PageResponseDto<PlatformStructureRes> getPlatformStructure(@ParameterObject @PageableDefault(page = 1) Pageable pageable) {
        return platformService.getPlatformStructure(pageable);
    }

    @GetMapping("/active-floor")
    @Operation(description = "Опустить платформу на один этаж")
    public PlatformActiveFloorRes getActiveFloor() {
       return platformService.getActiveFloor();
    }

    @PutMapping("/start")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(description = "Запустить работу платформы")
    public void start() {
        platformService.start();
    }

    @PutMapping("/finish")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(description = "Завершить работу платформы")
    public void finish() {
        platformService.finish();
    }

    @PutMapping("/down")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(description = "Опустить платформу на один этаж")
    public void downFloor() {
        platformService.downFloor();
    }

}
