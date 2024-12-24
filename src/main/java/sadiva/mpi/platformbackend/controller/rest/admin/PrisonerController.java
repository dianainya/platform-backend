package sadiva.mpi.platformbackend.controller.rest.admin;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import sadiva.mpi.platformbackend.dto.PageResponseDto;
import sadiva.mpi.platformbackend.dto.admin.prisoner.PrisonerCreateReq;
import sadiva.mpi.platformbackend.dto.admin.prisoner.PrisonerFilterParam;
import sadiva.mpi.platformbackend.dto.admin.prisoner.PrisonerRes;
import sadiva.mpi.platformbackend.dto.admin.prisoner.PrisonerUpdateReq;
import sadiva.mpi.platformbackend.service.PrisonerService;

import java.util.UUID;

@RestController
@RequestMapping("api/v1/private/prisoners")
@RequiredArgsConstructor
@PreAuthorize("hasAnyAuthority('admin, prisoner_register')")
public class PrisonerController {
    private final PrisonerService prisonerService;

    @PostMapping
    public PrisonerRes registerPrisoner(@RequestBody @Valid PrisonerCreateReq prisonerCreateOrUpdateReq) {
        return prisonerService.save(prisonerCreateOrUpdateReq);
    }

    @GetMapping("/{id}")
    public PrisonerRes getById(@PathVariable UUID id) {
        return prisonerService.getById(id);
    }

    @GetMapping
    public PageResponseDto<PrisonerRes> getPaginated(@PageableDefault(page = 1) @ParameterObject Pageable pageable,
                                                     @ModelAttribute @ParameterObject PrisonerFilterParam filterParam) {
        return prisonerService.getPaginated(pageable, filterParam);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable UUID id) {
        prisonerService.deleteById(id);

    }

    @PutMapping("/{id}")
    public PrisonerRes update(@PathVariable UUID id, @Valid @RequestBody PrisonerUpdateReq dto) {
        return prisonerService.update(id, dto);
    }

}
