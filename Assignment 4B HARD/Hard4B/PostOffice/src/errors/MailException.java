package errors;

/**
 * A type of semantics exception when there is an error with the mail (e.g. no mail)
 *
 * @author kayak
 * @version 1.0
 */
public class MailException extends SemanticsException{
    private static final long serialVersionUID = 1029851924951793L;

    /**
     * Creates an exception with an error message
     * @param message error message
     */
    public MailException(String message){
        super(message);
    }
}
