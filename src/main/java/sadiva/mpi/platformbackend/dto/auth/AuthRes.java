package sadiva.mpi.platformbackend.dto.auth;

import java.util.List;

public record AuthRes(
        String token,
        List<String> roles
) {
}
