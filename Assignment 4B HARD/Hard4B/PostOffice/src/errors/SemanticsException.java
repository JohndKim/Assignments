package errors;


/**
 * A type of post office exception that arises due to errors in semantics
 *
 * @author kayak
 * @version 1.0
 */
public class SemanticsException extends PostOfficeExceptions{
    private static final long serialVersionUID = 7124501125352160L;

    /**
     * Creates an exception with an error message
     * @param message error message
     */
    public SemanticsException(String message){
        super(message);
    }
}
