package errors;

/**
 * A type of semantics exception when trying to add an already existing user to the system
 *
 * @author kayak
 * @version 1.0
 */
public class AddUserException extends SemanticsException{
    private static final long serialVersionUID = 2525602424856323L;

    /**
     * Creates an exception with an error message
     * @param message error message
     */
    public AddUserException(String message) {
        super(message);
    }
}
