package sadiva.mpi.platformbackend.dto.bars;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

import java.util.UUID;

public record BarsAddScoreReq(
        UUID personId,
        @Min(value = 0)
        @Max(value = 100)
        Integer score
) {
}
