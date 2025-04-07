package lt.esdc.exception;

public class ShapeValidationException extends Exception {

    public ShapeValidationException(String message) {
        super(message);
    }

    public ShapeValidationException(String message, Throwable cause) {
        super(message, cause);
    }
}
