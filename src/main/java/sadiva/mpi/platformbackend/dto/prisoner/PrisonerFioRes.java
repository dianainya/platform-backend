package sadiva.mpi.platformbackend.dto.prisoner;

import java.util.UUID;

public record PrisonerFioRes(
        UUID id,
        String lastName,
        String firstName,
        String patronymic,
        Double rating,
        Boolean isAlive
) {
}
