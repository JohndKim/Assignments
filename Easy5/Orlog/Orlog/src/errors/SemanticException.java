package errors;

import java.io.Serial;

/**
 * An exception thrown when there is a semantic error
 *
 * @author kayak
 * @version 1.0
 */
public class SemanticException extends TaskException {
    @Serial
    private static final long serialVersionUID = 9084426277858712L;

    /**
     * Creates an exception with an error message
     *
     * @param message error message
     */
    public SemanticException(String message) {
        super(message);
    }
}
