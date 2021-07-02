package pe.com.hiper.bmatic.perfilagendamientows.web.models;


public class ErrorDTO {

    private final String type;

    private final String message;

    public ErrorDTO(String type, String message) {

        this.type = type;
        this.message = message == null || message.isEmpty() ? "An unknown error occurred" : message;
    }

    public ErrorDTO(Exception exception) {

        this(exception.getClass().getSimpleName(), exception.getMessage());
    }

    public String getType() {
        return type;
    }

    public String getMessage() {
        return message;
    }

}
