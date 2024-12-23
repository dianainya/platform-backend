package sadiva.mpi.platformbackend.controller.rest;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import sadiva.mpi.platformbackend.dto.PageResponseDto;
import sadiva.mpi.platformbackend.dto.menu.CurrentMenuRes;
import sadiva.mpi.platformbackend.dto.menu.DishPickupReq;
import sadiva.mpi.platformbackend.service.CurrentMenuService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/menu")
@RequiredArgsConstructor
public class CurrentMenuController {
    private final CurrentMenuService currentMenuService;

    @GetMapping
    @Operation(description = "Получение текущего меню")
    public PageResponseDto<CurrentMenuRes> getCurrentMenu(@ParameterObject @PageableDefault(page = 1) Pageable pageable) {
        return currentMenuService.getCurrentMenu(pageable);
    }

    @PutMapping("/pick-dishes")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(description = "Выбрать блюда из меню")
    public void pickDishes(@RequestBody List<DishPickupReq> dishPickupReqs) {
        currentMenuService.pickDishes(dishPickupReqs);
    }
}
