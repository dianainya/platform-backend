package sadiva.mpi.platformbackend.dto.admin.dish;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record Ingredient(
            @NotNull
            @Schema(description = "Идентификатор продукта")
            UUID productId,
            @NotNull
            @Schema(description = "Количество")
            Integer amount
    ) {
    }