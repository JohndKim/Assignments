package errors;

/**
 * A type of semantics exception when the password fails to meet the requirements
 *
 * @author kayak
 * @version 1.0
 */
public class PasswordException extends SemanticsException{
    private static final long serialVersionUID = 9289800234951793L;

    /**
     * Creates an exception with an error message
     * @param message error message
     */
    public PasswordException(String message){
        super(message);
    }
}
