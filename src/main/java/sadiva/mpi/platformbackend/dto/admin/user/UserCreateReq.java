package sadiva.mpi.platformbackend.dto.admin.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record UserCreateReq(
        @NotNull
        @NotBlank
        String username,
        String password,
        List<String> roles
) {
}
