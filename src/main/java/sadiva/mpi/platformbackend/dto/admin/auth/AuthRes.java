package sadiva.mpi.platformbackend.dto.admin.auth;

import java.util.List;

public record AuthRes(
        String token,
        List<String> roles
) {
}
