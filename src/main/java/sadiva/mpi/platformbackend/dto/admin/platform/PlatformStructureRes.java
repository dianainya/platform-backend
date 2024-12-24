package sadiva.mpi.platformbackend.dto.admin.platform;

import sadiva.mpi.platformbackend.dto.admin.prisoner.PrisonerFioRes;

public record PlatformStructureRes(
        Integer floor,
        PrisonerFioRes firstPrisoner,
        PrisonerFioRes secondPrisoner,
        Boolean isActive
) {
}
