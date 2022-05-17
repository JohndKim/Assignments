package errors;

/**
 * A type of post office exception where input fails to match necessary syntax
 *
 * @author kayak
 * @version 1.0
 */
public class SyntaxException extends PostOfficeExceptions {
    private static final long serialVersionUID = 5284161224821295L;

    /**
     * Creates an exception with an error message
     * @param message error message
     */
    public SyntaxException(String message) {
        super(message);
    }
}
