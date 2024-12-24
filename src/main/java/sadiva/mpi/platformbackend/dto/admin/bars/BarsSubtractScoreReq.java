package sadiva.mpi.platformbackend.dto.admin.bars;

import java.util.UUID;

public record BarsSubtractScoreReq(
        UUID personId,
        String violationCode
) {
}
