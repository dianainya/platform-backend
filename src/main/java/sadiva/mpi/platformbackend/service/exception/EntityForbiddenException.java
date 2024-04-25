package sadiva.mpi.platformbackend.service.exception;

public class EntityForbiddenException extends RuntimeException{
    public EntityForbiddenException(String message) {
        super(message);
    }
}
