package pe.com.hiper.bmatic.perfilagendamientows.web.errors;

public class BmaticException extends RuntimeException {

    /**
     * BmaticException
     */

    private static final long serialVersionUID = 1L;

    public BmaticException(final String message) {
        super(message);
    }
}
