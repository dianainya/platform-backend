package sadiva.mpi.platformbackend.dto.prisoner;

import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record PrisonerCreateReq(
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
        @Pattern(regexp = "^[0-9 ]+$", message = "Паспорт может содержать только цифры и пробелы")
        String passport,
        @NotNull
        @Min(value = 40, message = "Люди весом менее 40кг не могут попасть в платформу")
        @Max(value = 180, message = "Люди весом более 180кг не могут попасть в платформу")
        Float weight,
        @NotNull
        LocalDate birthDate,
        @NotNull
        String favoriteDishName,
        String password
) {
}
