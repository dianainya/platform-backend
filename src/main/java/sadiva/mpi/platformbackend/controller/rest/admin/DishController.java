package sadiva.mpi.platformbackend.controller.rest.admin;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import sadiva.mpi.platformbackend.dto.PageResponseDto;
import sadiva.mpi.platformbackend.dto.admin.dish.DishCreateOrUpdateReqDto;
import sadiva.mpi.platformbackend.dto.admin.dish.DishDtoRes;
import sadiva.mpi.platformbackend.dto.admin.dish.DishFilterParam;
import sadiva.mpi.platformbackend.service.DishService;

import java.util.UUID;

@RestController
@RequestMapping("api/v1/private/dishes")
@RequiredArgsConstructor
public class DishController {
    private final DishService dishService;

    @GetMapping
    public PageResponseDto<DishDtoRes> getPaginated(@ModelAttribute @ParameterObject DishFilterParam filterParam,
                                                    @PageableDefault(page = 1) @ParameterObject Pageable pageable) {
        return dishService.getPaginated(filterParam, pageable);
    }

    @GetMapping("/{id}")
    public DishDtoRes getById(@PathVariable UUID id) {
        return dishService.getById(id);
    }

    @PostMapping
    public DishDtoRes save(@RequestBody @Valid DishCreateOrUpdateReqDto dishCreateOrUpdateDto) {
        return dishService.save(dishCreateOrUpdateDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable UUID id) {
        dishService.deleteById(id);
    }

    @PutMapping("/{id}")
    public DishDtoRes update(@PathVariable UUID id, @RequestBody @Valid DishCreateOrUpdateReqDto dishCreateOrUpdateDto) {
        return dishService.update(id, dishCreateOrUpdateDto);
    }
}
