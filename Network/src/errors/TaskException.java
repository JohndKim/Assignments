package errors;

import java.io.Serial;

/**
 * An exception thrown whenever an error occurs in the task
 *
 * @author kayak
 * @version  1.0
 */
public abstract class TaskException extends Exception{
    @Serial
    private static final long serialVersionUID = 1195602992856340L;

    /**
     * Creates an exception with an error message
     * @param message error message
     */
    protected TaskException(String message) {
        super(message);
    }
}
