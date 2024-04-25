package sadiva.mpi.platformbackend.entity;

import java.util.List;

public record PlatformUserEntity(
        String username,
        String password,
        Boolean activated,
        List<String> roles
) {
}
