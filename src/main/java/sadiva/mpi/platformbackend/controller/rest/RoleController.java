package sadiva.mpi.platformbackend.controller.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sadiva.mpi.platformbackend.dto.user.RolesRes;
import sadiva.mpi.platformbackend.service.RoleService;

@RestController
@RequestMapping("api/v1/roles")
@RequiredArgsConstructor
public class RoleController {
    private final RoleService roleService;

    @GetMapping
    public RolesRes getAll() {
        return roleService.getAll();
    }
}
