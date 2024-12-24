package sadiva.mpi.platformbackend.dto.admin.dish;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record DishCreateOrUpdateReqDto(
        @NotNull
        @NotBlank
        @Schema(description = "Наименование блюда")
        String name,

        @Schema(description = "Описание блюда")
        String description,

        @NotNull
        @NotBlank
        @Schema(description = "Рецепт")
        String receipt,

        @NotNull
        @NotEmpty
        @Schema(description = "Ингредиенты")
        List<Ingredient> ingredients
) {

}
