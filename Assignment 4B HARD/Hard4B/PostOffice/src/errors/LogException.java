package errors;

/**
 * A type of semantics exception when users login incorrectly
 *
 * @author kayak
 * @version 1.0
 */
public class LogException extends SemanticsException{
    private static final long serialVersionUID = 9523501920751204L;

    /**
     * Creates an exception with an error message
     * @param message error message
     */
    public LogException(String message){
        super(message);
    }
}
