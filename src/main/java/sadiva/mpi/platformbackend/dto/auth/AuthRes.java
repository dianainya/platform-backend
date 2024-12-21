package sadiva.mpi.platformbackend.dto.auth;

public record AuthRes(
        String token,
        Object[] roles
) {
}
