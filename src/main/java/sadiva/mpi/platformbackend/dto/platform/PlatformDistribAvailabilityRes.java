package sadiva.mpi.platformbackend.dto.platform;

public record PlatformDistribAvailabilityRes(
        Boolean isAvailable,
        String message
) {
}
