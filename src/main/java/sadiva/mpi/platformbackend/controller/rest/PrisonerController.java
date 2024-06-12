package sadiva.mpi.platformbackend.controller.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import sadiva.mpi.platformbackend.dto.prisoner.PrisonerReq;

@RestController
@RequestMapping("api/v1/prisoners")
@RequiredArgsConstructor
//@PreAuthorize("hasAnyAuthority('admin, prisoner_register')")
public class PrisonerController {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void registerPrisoner(@RequestBody PrisonerReq prisonerReq) {

    }

}
