package errors;

/**
 * A class containing all error messages
 *
 * @author kayak
 * @version 1.0
 */
public final class Errors {
    /**
     * Error when wrong parameter is entered
     */
    public static final String WRONG_PARAMETER = "You have entered incorrect parameters";
    /**
     * Error when invalid command is entered
     */
    public static final String INVALID_COMMAND = "The command you have entered does not exist";
    /**
     * Error when an invalid player build is entered
     */
    public static final String INVALID_PLAYER_BUILD = "You cannot give these stats to the player";
    /**
     * Error when it is neither player's turn
     */
    public static final String NO_PLAYER_TURN = "It is currently no player's turn";
    /**
     * Error when an invalid ID is entered
     */
    public static final String INVALID_IDENTIFIER = "The identifier you have entered is incorrect";
    /**
     * Error when the player is trying to roll again
     */
    public static final String ALREADY_ROLLED = "You have already rolled";
    /**
     * Error when the god favor level entered is out of bounds
     */
    public static final String INVALID_LEVEL = "The god favor level you have entered is out of bounds";
    /**
     * Error when a god favor has already been chosen
     */
    public static final String ALREADY_CHOSEN = "You have already chosen a god favor";
    /**
     * Error when the player tries skip his turn without playing anything
     */
    public static final String HAS_NOT_PLAYED = "You have not rolled or chosen a god";
    /**
     * Error when the player tries to choose a god before rolling
     */
    public static final String HAS_NOT_ROLLED = "You have not rolled yet";
    /**
     * Error when the player tries to do more than one thing in a turn
     */
    public static final String WAIT_YOUR_TURN = "You can't do that this turn";
    /**
     * Error when the player is too poor
     */
    public static final String YOU_ARE_POOR = "You don't have enough god power to carry out this favor";

    private Errors() throws IllegalAccessException {
        throw new IllegalAccessException("Utility-class Constructor");
    }
}
