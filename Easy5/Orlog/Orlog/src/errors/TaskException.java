package errors;

import java.io.Serial;

/**
 * All errors thrown during an Orlog game, categorized by either semantic or syntax
 *
 * @author kayak
 * @version 1.0
 */
public abstract class TaskException extends Exception {
    @Serial
    private static final long serialVersionUID = 2525602424856323L;

    /**
     * Creates an exception with an error message
     * @param message error message
     */
    protected TaskException(String message) {
        super(message);
    }
}
