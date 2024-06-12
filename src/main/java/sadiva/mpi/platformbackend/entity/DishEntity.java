package sadiva.mpi.platformbackend.entity;

import java.util.List;
import java.util.UUID;

public record DishEntity(
        UUID id,
        String name,
        String description,
        String receipt,
        List<IngredientEntity> ingredients
) {
}
