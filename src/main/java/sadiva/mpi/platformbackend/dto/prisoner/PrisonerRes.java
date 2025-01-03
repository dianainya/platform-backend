package sadiva.mpi.platformbackend.dto.prisoner;

import sadiva.mpi.platformbackend.dto.dish.DishShortDtoRes;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record PrisonerRes(
        UUID id,
        String lastName,
        String firstName,
        String patronymic,
        Float weight,
        String passport,
        LocalDate birthDate,
        DishShortDtoRes favoriteDish,
        BigDecimal rating,
        Boolean isAlive
) {
}
