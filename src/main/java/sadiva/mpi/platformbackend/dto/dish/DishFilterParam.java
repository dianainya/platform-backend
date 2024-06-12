package sadiva.mpi.platformbackend.dto.dish;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Pattern;

import static sadiva.mpi.platformbackend.controller.HttpFilterConsts.NO_SQLI_REGEXP;

public record DishFilterParam(
        @Pattern(regexp = NO_SQLI_REGEXP, flags = {Pattern.Flag.CASE_INSENSITIVE}, message = "SQL Injection suspected")
        @Schema(description = "Наименование блюда для поиска")
        String name
) {
}
