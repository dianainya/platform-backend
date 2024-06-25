package sadiva.mpi.platformbackend.entity;

import java.util.UUID;

public record PrisonerFioEntity(
        UUID id,
        String lastName,
        String firstName,
        String patronymic
) {
}
