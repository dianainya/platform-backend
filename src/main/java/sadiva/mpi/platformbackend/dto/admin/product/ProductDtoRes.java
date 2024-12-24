package sadiva.mpi.platformbackend.dto.admin.product;

import java.util.UUID;

public record ProductDtoRes(
                UUID id,
                String name,
                String calories,
                Integer proteins,
                Integer fats,
                Integer carbohydrates,
                Integer weight,
                Integer amount
        ) {

}
