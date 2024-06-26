package sadiva.mpi.platformbackend.entity;

import java.math.BigDecimal;
import java.util.UUID;

public record PrisonerFioEntity(
        UUID id,
        String lastName,
        String firstName,
        String patronymic,
        BigDecimal rating
) {
}
