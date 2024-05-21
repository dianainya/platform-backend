package sadiva.mpi.platformbackend.dto.product;

import java.util.UUID;

public record ProductDtoRes
        (
                UUID id,
                String name,
                String calories,
                Integer proteins,
                Integer fats,
                Integer carbohydrates,
                Double availableWeight
        ) {

}
