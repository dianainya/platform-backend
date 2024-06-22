package sadiva.mpi.platformbackend.dto.bars;

import java.util.UUID;

public record BarsAddScoreReq(
        UUID personId,
        Integer score
) {
}
