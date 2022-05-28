package errors;

import java.io.Serial;

/**
 * An exception thrown when an error occurs while parsing the notation
 *
 * @author kayak
 * @version  1.0
 */
public class ParseException extends TaskException{
    @Serial
    private static final long serialVersionUID = 2995912452856163L;

    /**
     * Creates an exception with an error message
     *
     * @param message error message
     */
    public ParseException(String message) {
        super(message);
    }
}
