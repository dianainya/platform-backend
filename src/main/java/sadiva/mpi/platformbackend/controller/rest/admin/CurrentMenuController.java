package sadiva.mpi.platformbackend.controller.rest.admin;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sadiva.mpi.platformbackend.dto.PageResponseDto;
import sadiva.mpi.platformbackend.dto.admin.menu.CurrentMenuRes;
import sadiva.mpi.platformbackend.service.CurrentMenuService;

@RestController
@RequestMapping("/api/v1/private/menu")
@RequiredArgsConstructor
public class CurrentMenuController {
    private final CurrentMenuService currentMenuService;

    @GetMapping
    @Operation(description = "Получение текущего меню для админки")
    public PageResponseDto<CurrentMenuRes> getCurrentMenu(@ParameterObject @PageableDefault(page = 1) Pageable pageable) {
        return currentMenuService.getCurrentMenu(pageable);
    }

}
