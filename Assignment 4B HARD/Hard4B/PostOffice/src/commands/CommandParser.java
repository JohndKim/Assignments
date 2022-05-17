package commands;

import errors.Errors;
import errors.SyntaxException;

import java.util.List;

/**
 * A utility-class containing the syntax and regex for post office commands
 *
 * @author kayak
 * @version 1.0
 */
public class CommandParser {

    // COMMANDS

    public static final String ADD_CUSTOMER_COMMAND = "add-customer";
    public static final String ADD_MAILMAN_COMMAND = "add-mailman";
    public static final String ADD_AGENT_COMMAND = "add-agent";
    public static final String AUTHENTICATE_COMMAND = "authenticate";
    public static final String LOGOUT_COMMAND = "logout";
    public static final String SEND_MAIL_COMMAND = "send-mail";
    public static final String GET_MAIL_COMMAND = "get-mail";
    public static final String LIST_MAIL_COMMAND = "list-mail";
    public static final String LIST_PRICE_COMMAND = "list-price";
    public static final String RESET_PIN_COMMAND = "reset-pin";
    public static final String QUIT_COMMAND = "quit";

    // CHARACTERS

    public static final String SPACE = " ";
    public static final char SPACE_CHAR = ' ';
    public static final String SEMI_COLON = ";";
    public static final String OR = "|";
    public static final String FIRST_NAME = "[\\w]*"; // all letters
    public static final String LAST_NAME = "[\\w]*"; // all letters
    public static final String USERNAME = "[^;\\s]{4,9}"; // anything but a ";" or " ", 4-9 characters
    public static final String CUSTOMER_ID = "[^;\\s]{9}"; // anything but a ";" or " ", 9 characters
    public static final String MAILMAN_AGENT_ID = "[\\d]+"; // any positive integer
    public static final String PASSWORD = "[^;\\s]{4,9}"; // anything but a ";" or " ", 4-9 characters
    public static final String POSTAL_SERVICE = "[^;\\s]+"; // anything but a ";" or " "
    public static final String SUCCESS_WITHOUT_MESSAGE = "";
    public static final String SUCCESS_WITH_MESSAGE = "Okay";
    public static final String END_SESSION = "end";

    // REGEX

    public static final String REGEX_ADD_CUSTOMER_COMMAND = ADD_CUSTOMER_COMMAND + SPACE + FIRST_NAME + SEMI_COLON + LAST_NAME + SEMI_COLON + USERNAME + SEMI_COLON + PASSWORD + SEMI_COLON + CUSTOMER_ID;
    public static final String REGEX_ADD_MAILMAN_COMMAND = ADD_MAILMAN_COMMAND + SPACE + FIRST_NAME + SEMI_COLON + LAST_NAME + SEMI_COLON + MAILMAN_AGENT_ID + SEMI_COLON + PASSWORD;
    public static final String REGEX_ADD_AGENT_COMMAND =  ADD_AGENT_COMMAND + SPACE + FIRST_NAME + SEMI_COLON + LAST_NAME + SEMI_COLON + MAILMAN_AGENT_ID + SEMI_COLON + PASSWORD;
    public static final String REGEX_AUTHENTICATE_CUSTOMER_COMMAND = AUTHENTICATE_COMMAND + SPACE + USERNAME + SEMI_COLON + PASSWORD;
    public static final String REGEX_AUTHENTICATE_MAILMAN_AGENT_COMMAND = AUTHENTICATE_COMMAND + SPACE + MAILMAN_AGENT_ID + SEMI_COLON + PASSWORD;
    public static final String REGEX_AUTHENTICATE_COMMAND = REGEX_AUTHENTICATE_CUSTOMER_COMMAND + OR + REGEX_AUTHENTICATE_MAILMAN_AGENT_COMMAND;
    public static final String REGEX_LOGOUT_COMMAND = LOGOUT_COMMAND;
    public static final String REGEX_SEND_MAIL_CUSTOMER_COMMAND = SEND_MAIL_COMMAND + SPACE + POSTAL_SERVICE + SEMI_COLON + USERNAME;
    public static final String REGEX_SEND_MAIL_MAILMAN_COMMAND = SEND_MAIL_COMMAND + SPACE + POSTAL_SERVICE + SEMI_COLON + USERNAME + SEMI_COLON + USERNAME;
    public static final String REGEX_SEND_MAIL_COMMAND = REGEX_SEND_MAIL_CUSTOMER_COMMAND + OR + REGEX_SEND_MAIL_MAILMAN_COMMAND;
    public static final String REGEX_GET_MAIL_CUSTOMER_COMMAND = GET_MAIL_COMMAND;
    public static final String REGEX_GET_MAIL_MAILMAN_COMMAND = GET_MAIL_COMMAND + SPACE + USERNAME;
    public static final String REGEX_GET_MAIL_COMMAND = REGEX_GET_MAIL_CUSTOMER_COMMAND + OR + REGEX_GET_MAIL_MAILMAN_COMMAND;
    public static final String REGEX_LIST_MAIL_CUSTOMER_COMMAND = LIST_MAIL_COMMAND;
    public static final String REGEX_LIST_MAIL_MAILMAN_AGENT_COMMAND = LIST_MAIL_COMMAND + SPACE + USERNAME;
    public static final String REGEX_LIST_MAIL_COMMAND = REGEX_LIST_MAIL_CUSTOMER_COMMAND + OR + REGEX_LIST_MAIL_MAILMAN_AGENT_COMMAND;
    public static final String REGEX_LIST_PRICE_CUSTOMER_COMMAND = LIST_PRICE_COMMAND;
    public static final String REGEX_LIST_PRICE_MAILMAN_AGENT_COMMAND = LIST_PRICE_COMMAND + SPACE + USERNAME;
    public static final String REGEX_LIST_PRICE_COMMAND = REGEX_LIST_PRICE_CUSTOMER_COMMAND + OR + REGEX_LIST_PRICE_MAILMAN_AGENT_COMMAND;
    public static final String REGEX_RESET_PIN_COMMAND = RESET_PIN_COMMAND + SPACE + USERNAME + SEMI_COLON + PASSWORD;
    public static final String REGEX_QUIT_COMMAND = QUIT_COMMAND;

    /**
     * Checks for correct syntax
     *
     * @param inputList input in the format of a list
     * @param input the entire input
     * @throws SyntaxException if input fails to match the command's regex
     */
    public static String checkCommand(List<String> inputList, String input) throws SyntaxException {
        checkSpace(input);
        if (!input.matches(Commands.getCommand(inputList.get(0)).getCommandRegex())) throw new SyntaxException(Errors.WRONG_PARAMETERS);
        return inputList.get(0);
    }

    /**
     * Checks if the user pressed enter or spaces only
     *
     * @param input string input
     * @throws SyntaxException wrong syntax
     */
    public static void checkSpace(String input) throws SyntaxException {
        if (input.length() == 0 || input.charAt(0) == (SPACE_CHAR)) throw new SyntaxException(Errors.INVALID_COMMAND);
    }

    private CommandParser() throws IllegalAccessException {
        throw new IllegalAccessException("Utility-class Constructor");
    }
}