package lt.esdc.shape.exception;

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
}
