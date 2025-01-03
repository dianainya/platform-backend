package sadiva.mpi.platformbackend.entity;

import java.util.List;
import java.util.UUID;

public record PlatformUserEntity(
        UUID id,
        String username,
        String password,
        Boolean activated,
        List<String> roles
) {
}
