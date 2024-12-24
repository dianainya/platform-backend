package sadiva.mpi.platformbackend.dto.admin.platform;

public record PlatformDistribAvailabilityRes(
        Boolean isAvailable,
        String message
) {
}
