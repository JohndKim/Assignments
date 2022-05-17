package errors;

/**
 * A type of semantics exception when the user already exists in the system
 *
 * @author kayak
 * @version 1.0
 */
public class ExistException extends SemanticsException{
    private static final long serialVersionUID = 9280102424859318L;

    /**
     * Creates an exception with an error message
     * @param message error message
     */
    public ExistException(String message) {
        super(message);
    }
}
