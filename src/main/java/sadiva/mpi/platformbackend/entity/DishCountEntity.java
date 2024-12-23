package sadiva.mpi.platformbackend.entity;

import java.util.UUID;

public record DishCountEntity(
        UUID dishId,
        Integer dishCount
) {
}
