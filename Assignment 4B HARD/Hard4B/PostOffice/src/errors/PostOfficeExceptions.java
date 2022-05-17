package errors;

/**
 * Represents all exceptions within the post office
 *
 * @author kayak
 * @version 1.0
 */
public abstract class PostOfficeExceptions extends Exception{
    private static final long serialVersionUID = 2385101924851293L;

    /**
     * Creates an exception with an error message
     * @param message error message
     */
    protected PostOfficeExceptions(String message){
        super(message);
    }
}
