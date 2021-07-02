package pe.com.hiper.bmatic.perfilagendamientows.web.errors;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import pe.com.hiper.bmatic.perfilagendamientows.web.models.ErrorDTO;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler {

    private final Logger log = LogManager.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler({BmaticException.class})
    public ResponseEntity<ErrorDTO> handleOwnException(Exception exception, HttpServletRequest request) {

        log.error(exception.getMessage());

        return new ResponseEntity<>(new ErrorDTO(exception), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({ControllerException.class})
    public ResponseEntity<ErrorDTO> handleControllerException(ControllerException exception,
                                                              HttpServletRequest request) {

        log.error(exception.getMessage());

        return exception.getResponse();
    }

    @ExceptionHandler({HttpMessageNotReadableException.class})
    public ResponseEntity<ErrorDTO> handleHttpMessageNotReadableException(HttpMessageNotReadableException exception,
                                                                          HttpServletRequest request) {

        log.error(exception.getMessage());

        Throwable throwable = exception.getCause();

        return new InvalidRequestException(throwable.getClass().getSimpleName()).getResponse();
    }

    @ExceptionHandler({MethodArgumentTypeMismatchException.class})
    public ResponseEntity<ErrorDTO> handleMethodArgumentTypeMismatchException(
            MethodArgumentTypeMismatchException exception, HttpServletRequest request) {

        log.error(exception.getMessage());

        return new InvalidParamValueException(exception.getName(), exception.getValue()).getResponse();
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<ErrorDTO> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception,
                                                                          HttpServletRequest request) {

        log.error(exception.getMessage());

        BindingResult result = exception.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors();

        return new InvalidParamValueException(fieldErrors.get(0)).getResponse();
    }

}
