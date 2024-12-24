package sadiva.mpi.platformbackend.dto.admin.product;

import jakarta.validation.constraints.Pattern;

import static sadiva.mpi.platformbackend.controller.HttpFilterConsts.NO_SQLI_REGEXP;

public record ProductFilterParam(
        @Pattern(regexp = NO_SQLI_REGEXP, flags = {Pattern.Flag.CASE_INSENSITIVE}, message = "SQL Injection suspected")

        String search
) {
}
