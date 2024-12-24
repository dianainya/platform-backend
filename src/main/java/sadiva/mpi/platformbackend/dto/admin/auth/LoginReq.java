package sadiva.mpi.platformbackend.dto.admin.auth;

import java.util.List;

public record LoginReq(
        String username,
        String password
) {
}
