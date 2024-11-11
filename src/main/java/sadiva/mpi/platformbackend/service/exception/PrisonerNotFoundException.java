package sadiva.mpi.platformbackend.service.exception;

import java.util.UUID;

public class PrisonerNotFoundException extends NotFoundException {
    public PrisonerNotFoundException(UUID prisonerId) {
        super("Prisoner with id " + prisonerId + " not found");
    }
}
