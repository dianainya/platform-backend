package sadiva.mpi.platformbackend.controller.rest.publics;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import sadiva.mpi.platformbackend.dto.PageResponseDto;
import sadiva.mpi.platformbackend.dto.admin.menu.CurrentMenuRes;
import sadiva.mpi.platformbackend.dto.admin.menu.DishPickupReq;
import sadiva.mpi.platformbackend.dto.publics.prisonser.PrisonerFloorRes;
import sadiva.mpi.platformbackend.service.MyPrisonerService;

import java.util.List;

@RestController
@RequestMapping("api/v1/me/prisoners")
@RequiredArgsConstructor
public class MyPrisonerController {
    private final MyPrisonerService prisonerService;

    @GetMapping("/floor")
    public PrisonerFloorRes getMyFloor(@RequestParam String username) {
        return prisonerService.getMyFloor(username);
    }

    @GetMapping("/current-menu")
    @Operation(description = "Получение текущего меню с проверкой что заключенный может")
    public PageResponseDto<CurrentMenuRes> getCurrentMenu(@RequestParam String username, @ParameterObject @PageableDefault(page = 1) Pageable pageable) {
        return prisonerService.getCurrentMenu(username, pageable);
    }


    @PutMapping("/pick-dishes")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(description = "Выбрать блюда из меню")
    public void pickDishes(@RequestParam String username, @RequestBody List<DishPickupReq> dishPickupReqs) {
        prisonerService.pickDishes(username, dishPickupReqs);
    }
}
