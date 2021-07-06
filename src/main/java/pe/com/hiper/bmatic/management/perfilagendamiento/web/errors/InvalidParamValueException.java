package pe.com.hiper.bmatic.management.perfilagendamiento.web.errors;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;

public class InvalidParamValueException extends ControllerException {

    /**
     * InvalidParamValueException
     */
    private static final long serialVersionUID = 1L;
    private static final String TEMPLATE = "Invalid value '%s' for param '%s'";

    public InvalidParamValueException(String param, Object value) {
        super(HttpStatus.BAD_REQUEST, String.format(TEMPLATE, value, param));
    }

    public InvalidParamValueException(FieldError error) {
        this(error.getField(), error.getRejectedValue());
    }

}
