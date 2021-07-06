package pe.com.hiper.bmatic.management.perfilagendamiento.web.errors;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import pe.com.hiper.bmatic.management.perfilagendamiento.web.models.ErrorDTO;

public abstract class ControllerException extends RuntimeException {

    /**
     * ControllerException
     */
    private static final long serialVersionUID = 1922901753796472026L;
    private final HttpStatus status;
    private final String message;

    @ExceptionHandler({ BmaticException.class })
    public ResponseEntity<ErrorDTO> handleOwnException(Exception exception) {

        return new ResponseEntity<>(new ErrorDTO(exception), HttpStatus.BAD_REQUEST);
    }

    public ControllerException(final HttpStatus status, final String message) {
        super(message);
        this.status = status;
        this.message = message;
    }

    public ResponseEntity<ErrorDTO> getResponse() {
        return ResponseEntity.status(status).contentType(MediaType.APPLICATION_JSON)
                .body(new ErrorDTO(this.getClass().getSimpleName(), message));
    }
}