package errors;

import java.io.Serial;

/**
 * A type of semantic exception where the player possesses invalid stats (e.g. health = -1)
 *
 * @author kayak
 * @version 1.0
 */
public class PlayerBuildException extends SemanticException {
    @Serial
    private static final long serialVersionUID = 8884246277851109L;

    /**
     * Creates an exception with an error message
     *
     * @param message error message
     */
    public PlayerBuildException(String message) {
        super(message);
    }
}
