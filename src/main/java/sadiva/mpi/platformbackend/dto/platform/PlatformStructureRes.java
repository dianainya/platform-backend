package sadiva.mpi.platformbackend.dto.platform;

import sadiva.mpi.platformbackend.dto.prisoner.PrisonerFioRes;

public record PlatformStructureRes(
        Integer floor,
        PrisonerFioRes firstPrisoner,
        PrisonerFioRes secondPrisoner,
        Boolean isActive
) {
}
