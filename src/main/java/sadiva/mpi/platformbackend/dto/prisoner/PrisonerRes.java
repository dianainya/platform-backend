package sadiva.mpi.platformbackend.dto.prisoner;

import sadiva.mpi.platformbackend.dto.dish.DishShortDtoRes;

import java.time.LocalDate;
import java.util.UUID;

public record PrisonerRes(
        UUID id,
        String lastName,
        String firstName,
        String patronymic,
        Float weight,
        LocalDate birthDate,
        DishShortDtoRes favoriteDish
) {
}
