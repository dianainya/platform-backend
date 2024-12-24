package sadiva.mpi.platformbackend.dto.admin.product;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProductCreateOrUpdateDtoReq(
        @NotNull
        @NotBlank
        @Schema(description = "Наименование продукта")
        String name,

        @NotNull
        @Schema(description = "Калории")
        @NotNull
        int calories,

        @Schema(description = "Белки")
        @NotNull
        int proteins,

        @Schema(description = "Жиры")
        @NotNull
        int fats,

        @Schema(description = "Углеводы")
        @NotNull
        int carbohydrates,

        @NotNull
        @Schema(description = "Вес")
        int weight
) {
}
