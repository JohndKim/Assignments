package errors;

import java.io.Serial;

/**
 * A type of semantic exception where the player attempts an illegal move
 *
 * @author kayak
 * @version 1.0
 */
public class PlayerException extends SemanticException {
    @Serial
    private static final long serialVersionUID = 8884246277851109L;

    /**
     * Creates an exception with an error message
     *
     * @param message error message
     */
    public PlayerException(String message) {
        super(message);
    }
}
