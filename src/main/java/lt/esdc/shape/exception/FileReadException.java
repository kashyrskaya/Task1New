package lt.esdc.shape.exception;

/**
 * Exception thrown when an error occurs while reading a file.
 */
public class FileReadException extends Exception {
    /**
     * Constructs a FileReadException with the specified message and cause.
     *
     * @param message the detail message
     * @param cause   the cause of the exception
     */
    public FileReadException(String message, Throwable cause) {
        super(message, cause);
    }
}
