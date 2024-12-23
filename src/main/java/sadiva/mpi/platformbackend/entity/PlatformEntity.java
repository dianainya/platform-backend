package sadiva.mpi.platformbackend.entity;

public record PlatformEntity(
        Integer floor,
        PrisonerFioEntity firstPrisoner,
        PrisonerFioEntity secondPrisoner,
        Boolean isActive
) {
}
