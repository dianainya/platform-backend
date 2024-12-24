package sadiva.mpi.platformbackend.controller.rest.admin;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import sadiva.mpi.platformbackend.dto.PageResponseDto;
import sadiva.mpi.platformbackend.dto.admin.user.RolesReq;
import sadiva.mpi.platformbackend.dto.admin.user.UserCreateReq;
import sadiva.mpi.platformbackend.dto.admin.user.UserRes;
import sadiva.mpi.platformbackend.service.UserService;

import java.util.UUID;

@RestController
@RequestMapping("api/v1/private/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping
    public PageResponseDto<UserRes> getPaginated(@PageableDefault(page = 1) @ParameterObject Pageable pageable) {
        return userService.getPaginated(pageable);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void save(@RequestBody @Valid UserCreateReq userCreateReq) {
        userService.save(userCreateReq);
    }

    @PatchMapping("{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateRoles(@PathVariable("userId") UUID userId,
                            @RequestBody @Valid RolesReq rolesReq) {
        userService.updateRoles(userId, rolesReq);
    }

    @DeleteMapping("{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("userId") UUID userId) {
        userService.delete(userId);
    }
}
