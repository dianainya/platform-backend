package sadiva.mpi.platformbackend.service.exception;

public class ViolationNotFoundException extends NotFoundException {
    public ViolationNotFoundException(String s) {
        super("Violation with code " + s + " not found");
    }
}
