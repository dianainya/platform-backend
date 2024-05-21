package sadiva.mpi.platformbackend.dto.product;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record ProductCreateOrUpdateDtoReq(
        @Schema(description = "Наименование продукта")
        String name,
        @Schema(description = "Калории")
        Integer calories,
        @Schema(description = "Белки")
        Integer proteins,
        @Schema(description = "Жиры")
        Integer fats,
        @Schema(description = "Углеводы")
        Integer carbohydrates,
        @Schema(description = "Вес")
        Float weight,
        @Schema(description = "Количество")
        Integer amount
) {
}
