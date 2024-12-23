package sadiva.mpi.platformbackend.dto.user;

import java.util.List;
import java.util.UUID;

public record UserRes(
        UUID id,
        String username,
        Boolean activated,
        List<String> roles
) {
}
