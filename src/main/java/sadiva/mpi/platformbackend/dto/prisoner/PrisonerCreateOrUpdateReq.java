package sadiva.mpi.platformbackend.dto.prisoner;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.UUID;

public record PrisonerCreateOrUpdateReq(
        @NotNull
        @NotBlank
        String lastName,
        @NotNull
        @NotBlank
        String firstName,
        @NotNull
        @NotBlank
        String patronymic,
        @NotNull
        @NotBlank
        String passport,
        @NotNull
        Float weight,
        @NotNull
        LocalDate birthDate,
        @NotNull
        UUID favoriteDish
) {
}
