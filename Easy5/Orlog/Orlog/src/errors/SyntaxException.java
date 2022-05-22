package errors;

import java.io.Serial;

/**
 * An exception thrown when input fails to match correct syntax
 *
 * @author kayak
 * @version 1.0
 */
public class SyntaxException extends TaskException {
    @Serial
    private static final long serialVersionUID = 5125606224850098L;

    /**
     * Creates an exception with an error message
     *
     * @param message error message
     */
    public SyntaxException(String message) {
        super(message);
    }
}
