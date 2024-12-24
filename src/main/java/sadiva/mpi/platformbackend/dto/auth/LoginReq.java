package sadiva.mpi.platformbackend.dto.auth;

import java.util.List;

public record LoginReq(
        String username,
        String password
) {
}
