package sadiva.mpi.platformbackend.controller.rest;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
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
}
