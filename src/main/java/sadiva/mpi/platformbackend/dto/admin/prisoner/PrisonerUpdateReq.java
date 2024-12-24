package sadiva.mpi.platformbackend.dto.admin.prisoner;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record PrisonerUpdateReq(
        String password,
        @NotNull
        Double weight,
        @NotNull
        String favoriteDishName,
        Boolean isAlive
) {
}
