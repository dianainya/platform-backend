package sadiva.mpi.platformbackend.dto.bars;

import java.util.UUID;

public record BarsSubtractScoreReq(
        UUID personId,
        String violationCode
) {
}
