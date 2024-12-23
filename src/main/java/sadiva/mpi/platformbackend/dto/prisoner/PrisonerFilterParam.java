package sadiva.mpi.platformbackend.dto.prisoner;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Pattern;

import static sadiva.mpi.platformbackend.controller.HttpFilterConsts.NO_SQLI_REGEXP;

public record PrisonerFilterParam(
        @Pattern(regexp = NO_SQLI_REGEXP, flags = {Pattern.Flag.CASE_INSENSITIVE}, message = "SQL Injection suspected")
        String search,
        @Schema(description = "Флаг отображения только заключенных с блюдами, у которых уже есть рецепт")
        Boolean isDishImplemented
) {
}
