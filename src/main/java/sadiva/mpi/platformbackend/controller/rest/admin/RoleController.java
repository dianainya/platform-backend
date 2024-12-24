package sadiva.mpi.platformbackend.controller.rest.admin;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sadiva.mpi.platformbackend.dto.admin.user.RolesRes;
import sadiva.mpi.platformbackend.service.RoleService;

@RestController
@RequestMapping("api/v1/private/roles")
@RequiredArgsConstructor
public class RoleController {
    private final RoleService roleService;

    @GetMapping
    public RolesRes getAll() {
        return roleService.getAll();
    }
}
