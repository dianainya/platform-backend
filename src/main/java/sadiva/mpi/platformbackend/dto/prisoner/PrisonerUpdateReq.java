package sadiva.mpi.platformbackend.dto.prisoner;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record PrisonerUpdateReq(

        @NotNull
        Double weight,
        @NotNull
        String favoriteDishName,
        Boolean isAlive
) {
}
