package sadiva.mpi.platformbackend.entity;

import java.time.LocalDate;
import java.util.UUID;

public record PrisonerEntity(
        UUID id,
        String lastName,
        String firstName,
        String patronymic,
        String passport,
        Double weight,
        LocalDate birthDate,
        DishShortEntity favoriteDish
) {
}
