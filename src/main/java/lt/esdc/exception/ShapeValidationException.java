package lt.esdc.exception;

/**
 * Exception thrown when a shape fails validation.
 */
public class ShapeValidationException extends Exception {

    /**
     * Constructs a ShapeValidationException with the specified message.
     *
     * @param message the detail message
     */
    public ShapeValidationException(String message) {
        super(message);
    }

    /**
     * Constructs a ShapeValidationException with the specified message and cause.
     *
     * @param message the detail message
     * @param cause the cause of the exception
     */
    public ShapeValidationException(String message, Throwable cause) {
        super(message, cause);
    }
}
