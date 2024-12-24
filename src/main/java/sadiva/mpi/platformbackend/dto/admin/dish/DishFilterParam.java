package sadiva.mpi.platformbackend.dto.admin.dish;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import static sadiva.mpi.platformbackend.controller.HttpFilterConsts.NO_SQLI_REGEXP;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DishFilterParam {
    @Pattern(regexp = NO_SQLI_REGEXP, flags = {Pattern.Flag.CASE_INSENSITIVE}, message = "SQL Injection suspected")
    @Schema(description = "Наименование блюда для поиска")
    private String name;
    private Boolean isImplemented = true;
}
