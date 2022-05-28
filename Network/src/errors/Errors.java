package errors;

/**
 * A class containing all errors
 *
 * @author kayak
 * @version 1.0
 */
public final class Errors {
    /**
     * Error thrown when IP is invalid
     */
    public static final String INVALID_IP = "The IP you have entered is invalid";
    /**
     * Error thrown when bracket notation is invalid
     */
    public static final String INVALID_FORMAT = "The format of IPs you have entered is invalid";
    /**
     * Error thrown when trying to access a non-existent ip
     */
    public static final String NONEXISTENT_IP = "The IP you have entered does not exist in the network";

    private Errors() throws IllegalStateException {
        throw new IllegalStateException();
    }
}
