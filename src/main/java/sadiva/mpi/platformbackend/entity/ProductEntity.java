package sadiva.mpi.platformbackend.entity;

import java.math.BigDecimal;
import java.util.UUID;

public record ProductEntity(
        UUID id,
        String name,
        Integer calories,
        Integer proteins,
        Integer fats,
        Integer carbohydrates,
        Integer weight,
        BigDecimal amount
) {
}
