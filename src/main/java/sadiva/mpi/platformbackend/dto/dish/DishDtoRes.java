package sadiva.mpi.platformbackend.dto.dish;

import sadiva.mpi.platformbackend.dto.product.ProductDtoRes;

import java.util.List;
import java.util.UUID;

public record DishDtoRes(
        UUID id,
        String name,
        String description,
        String receipt,
        List<IngredientDtoRes> ingredients
) {
    public record IngredientDtoRes(
            Integer amount,
            ProductDtoRes product
    ) {
    }
}
