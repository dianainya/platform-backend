package sadiva.mpi.platformbackend.dto.prisoner;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.UUID;

public record PrisonerReq(
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
        Float height,
        @NotNull
        Float weight,
        @NotNull
        LocalDate birthDay,
        @NotNull
        UUID favoriteDishId
) {
}
