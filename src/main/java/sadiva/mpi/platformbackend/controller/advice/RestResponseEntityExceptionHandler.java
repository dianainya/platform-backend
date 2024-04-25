package sadiva.mpi.platformbackend.controller.advice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import sadiva.mpi.platformbackend.dto.ErrorResponse;
import sadiva.mpi.platformbackend.service.exception.ConflictException;
import sadiva.mpi.platformbackend.service.exception.EntityForbiddenException;
import sadiva.mpi.platformbackend.service.exception.NotFoundException;
import sadiva.mpi.platformbackend.service.exception.ValidationException;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
@Slf4j
public class RestResponseEntityExceptionHandler {

    private static final String DATA_ACCESS_ERROR_MESSAGE = "Error while accessing data, underlying exception class is ";

    @ExceptionHandler(EntityForbiddenException.class)
    public ResponseEntity<ErrorResponse> handleEntityForbiddenException(EntityForbiddenException ex) {
        return buildErrorResponse(ex, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorResponse> handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
        return buildErrorResponse(ex, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationErrorResponse> handleValidationException(MethodArgumentNotValidException ex) {
        List<ValidationErrorResponse.ValidationError> errors = new ArrayList<>();

        for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
            String fieldName = fieldError.getField();
            String message = fieldError.getDefaultMessage();
            errors.add(new ValidationErrorResponse.ValidationError(fieldName, message));
        }

        for (ObjectError objectError : ex.getBindingResult().getGlobalErrors()) {
            String message = objectError.getDefaultMessage();
            errors.add(new ValidationErrorResponse.ValidationError("", message));
        }

        ValidationErrorResponse errorResponse = new ValidationErrorResponse("Validation failed", errors);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler({ValidationException.class})
    public ResponseEntity<ErrorResponse> handleValidationException(ValidationException ex) {
        return buildErrorResponse(ex, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({AccessDeniedException.class})
    public ResponseEntity<ErrorResponse> handleAccessDeniedException(AccessDeniedException ex) {
        return buildErrorResponse(ex, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler({NotFoundException.class})
    public ResponseEntity<ErrorResponse> handleNotFoundException(NotFoundException ex) {
        return buildErrorResponse(ex, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({ConflictException.class})
    public ResponseEntity<ErrorResponse> handleConflictException(ConflictException ex) {
        return buildErrorResponse(ex, HttpStatus.CONFLICT);
    }

    @ExceptionHandler({DataAccessException.class})
    public ResponseEntity<ErrorResponse> handleDataAccessException(DataAccessException ex) {
        return buildErrorResponse(buildErrorMessage(ex), HttpStatus.BAD_REQUEST);
    }

    private ResponseEntity<ErrorResponse> buildErrorResponse(Exception ex, HttpStatus status) {
        ErrorResponse errorResponse = new ErrorResponse(ex.getMessage());
        return ResponseEntity.status(status).body(errorResponse);
    }

    private ResponseEntity<ErrorResponse> buildErrorResponse(String message, HttpStatusCode status) {
        ErrorResponse badRequestResponse = new ErrorResponse(message);
        return ResponseEntity.status(status).body(badRequestResponse);
    }

    private String buildErrorMessage(DataAccessException ex) {
        if (ex.getCause() != null && ex.getCause().getMessage() != null) {
            return ex.getCause().getMessage();
        } else {
            return DATA_ACCESS_ERROR_MESSAGE + ex.getClass().getName();
        }
    }

    public record ValidationErrorResponse(
            String message,
            List<ValidationError> errors
    ) {
        public record ValidationError(
                String field,
                String message
        ) {
        }
    }
}
