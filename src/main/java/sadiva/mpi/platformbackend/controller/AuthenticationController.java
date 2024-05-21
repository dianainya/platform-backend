package sadiva.mpi.platformbackend.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import sadiva.mpi.platformbackend.dto.auth.AuthReq;
import sadiva.mpi.platformbackend.dto.auth.AuthRes;
import sadiva.mpi.platformbackend.service.UserService;

@RestController
@RequestMapping("api/v1")
@RequiredArgsConstructor
public class AuthenticationController {
    private final UserService userService;

    @PostMapping("/authorize")
    @Operation(summary = "Register user", description = "Create new user")
    @ResponseStatus(HttpStatus.CREATED)
    public void authorize(@RequestBody AuthReq authReq) {
        userService.authorize(authReq);
    }

    @PostMapping("/authenticate")
    @Operation(summary = "Log in user", description = "Create JWT token for authenticated user")
    public AuthRes createAuthenticationToken(@RequestBody AuthReq authReq) {
        return userService.createAuthenticationToken(authReq);
    }
}
