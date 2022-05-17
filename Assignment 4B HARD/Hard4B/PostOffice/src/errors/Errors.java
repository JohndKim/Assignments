package errors;

/**
 * A utility class representing all error messages
 *
 * @author kayak
 * @version 1.0
 */
public final class Errors {
    public static final String INVALID_COMMAND = "The command you have entered does not exist";
    public static final String SYSTEM_IN_USE = "A user is already logged in; you must log out first before using that command";
    public static final String WRONG_PARAMETERS = "Enter the correct parameters";
    public static final String PERSON_EXISTS = "This person already exists in the system";
    public static final String INCORRECT_CREDENTIALS = "Your username or password is incorrect";
    public static final String ALREADY_LOGGED_OUT = "The user you are trying to log out has already been logged out";
    public static final String NO_PERMISSION = "You don't have enough permission to use this command";
    public static final String NAME_ID_MATCHES = "You can't have your username and ID matching";
    public static final String CUSTOMER_DOES_NOT_EXIST = "The customer username you have entered does not exist";
    public static final String NO_MAIL = "You have no mail";
    public static final String NO_CUSTOMER_LOGGED_IN = "No customer is currently logged in";
    public static final String NO_AGENT_LOGGED_IN = "No agent is currently logged in";
    public static final String PASSWORD_FAIL = "Password does not follow the rules";
    public static final String NO_MAIL_SENT = "You have no amount due as you have not yet sent mail";
    public static final String LOG_OUT_FAIL = "Error, failed to log you out";

    private Errors() throws IllegalStateException{
        throw new IllegalStateException("Utility-class Constructor");
    }
}
