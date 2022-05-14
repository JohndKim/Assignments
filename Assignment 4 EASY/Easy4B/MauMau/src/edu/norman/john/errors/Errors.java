package edu.norman.john.errors;

/**
 * Contains all error messages
 *
 * @author kayak
 * @version 1.0
 */
public class Errors {
    public static final String INVALID_COMMAND = "You have entered an invalid command";
    public static final String WRONG_TURN = "This is not your turn";
    public static final String WRONG_CARD = "You don't have this card";
    public static final String ILLEGAL_CARD = "You cannot play this card; %s can't be placed on %s";
    public static final String ILLEGAL_COMMAND_USE = "You have used this command wrong, enter the right parameters";

    private Errors() throws IllegalAccessException {
        throw new IllegalStateException("Utility-class Constructor");
    }
}
