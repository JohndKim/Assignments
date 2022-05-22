package errors;

import java.io.Serial;

/**
 * A type of semantic exception thrown when an illegal move is attempted
 *
 * @author kayak
 * @version 1.0
 */
public class IllegalMoveException extends SemanticException {
    @Serial
    private static final long serialVersionUID = 1234254337221109L;

    /**
     * Creates an exception with an error message
     *
     * @param message error message
     */
    public IllegalMoveException(String message) {
        super(message);
    }
}
