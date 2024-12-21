package sadiva.mpi.platformbackend.dto.auth;

import java.util.List;

public record AuthReq(
        String username,
        String password,
        List<String> roles
) {
}
