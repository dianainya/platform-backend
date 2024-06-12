package sadiva.mpi.platformbackend.entity;

import jooq.sadiva.mpi.platformbackend.tables.pojos.Product;

public record IngredientEntity(
        Integer amount,
        Product product
) {
}
