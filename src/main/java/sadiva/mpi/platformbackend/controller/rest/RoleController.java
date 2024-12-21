package sadiva.mpi.platformbackend.controller.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import sadiva.mpi.platformbackend.dto.auth.AuthRes;
import sadiva.mpi.platformbackend.dto.roles.RolesReq;
import sadiva.mpi.platformbackend.service.UserService;

import java.util.UUID;

@RestController
@RequestMapping("api/v1/roles")
@RequiredArgsConstructor
public class RoleController {
    private final UserService userService;


//    @PutMapping("/{userId}")
//    @Operation(summary = "assign rolesReq")
//    public AuthRes assignRoles(@PathVariable UUID userId,
//                               @Parameter @ModelAttribute RolesReq rolesReq) {
//        return userService.assignRoles(userId, rolesReq);
//    }
}
