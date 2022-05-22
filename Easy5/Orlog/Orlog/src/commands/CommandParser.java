package commands;

import errors.Errors;
import errors.SyntaxException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * An enum containing command names and regex
 * Also checks if inputted command is valid
 *
 * @author kayak
 * @version 1.0
 */
public final class CommandParser {



    //              COMMANDS


    /**
     * Print command
     */
    public static final String PRINT_COMMAND = "print";
    /**
     * Roll command
     */
    public static final String ROLL_COMMAND = "roll";
    /**
     * Turn command
     */
    public static final String TURN_COMMAND = "turn";
    /**
     * God favor command
     */
    public static final String GOD_FAVOR_COMMAND = "godfavor";
    /**
     * Evaluate command
     */
    public static final String EVALUATE_COMMAND = "evaluate";
    /**
     * Quit command
     */
    public static final String QUIT_COMMAND = "quit";



    //              CHARACTERS


    /**
     * When a command is executed successfully with a message
     */
    public static final String SUCCESS_WITH_MESSAGE = "Okay";
    /**
     * When a command is executed successfully without a message
     */
    public static final String SUCCESS_WITHOUT_MESSAGE = "";
    /**
     * Separator for player info
     */
    public static final String PRINT_SEPARATOR = ";";
    /**
     * Prints new line
     */
    public static final String NEW_LINE = "\n";
    /**
     * String format for a win message
     */
    public static final String WIN_MESSAGE = "%s wins";
    /**
     * Draw message
     */
    public static final String DRAW = "draw";
    /**
     * Space character
     */
    public static final char SPACE_CHAR = ' ';
    /**
     * Space string
     */
    public static final String SPACE = " ";
    /**
     * Semi colon
     */
    public static final String SEMICOLON = ";";
    /**
     * Regex for a space or semi colon
     */
    public static final String SPACE_OR_SEMICOLON = "[\\s;]+"; // space or semi colon
    /**
     * Regex for a single roll
     */
    public static final String ROLL_PARAMETER = "[\\w]{2,3}"; // any 2-3 letters
    /**
     * Regex for a god favor ID
     */
    public static final String GOD_ID = "[\\w]{2}"; // any 2 letters
    /**
     * Regex for a god favor level
     */
    public static final String FAVOR_LEVEL = "[1-3]"; // any digit 1-3



    //              REGEX


    /**
     * Print command regex
     */
    public static final String REGEX_PRINT_COMMAND = PRINT_COMMAND;
    /**
     * Roll command regex
     */
    public static final String REGEX_ROLL_COMMAND = ROLL_COMMAND + SPACE
            + ROLL_PARAMETER + SEMICOLON + ROLL_PARAMETER + SEMICOLON + ROLL_PARAMETER
            + SEMICOLON + ROLL_PARAMETER + SEMICOLON + ROLL_PARAMETER + SEMICOLON + ROLL_PARAMETER;
    /**
     * Turn command regex
     */
    public static final String REGEX_TURN_COMMAND = TURN_COMMAND;
    /**
     * God favor command regex
     */
    public static final String REGEX_GOD_FAVOR_COMMAND = GOD_FAVOR_COMMAND + SPACE + GOD_ID + SEMICOLON + FAVOR_LEVEL;
    /**
     * Evaluate command regex
     */
    public static final String REGEX_EVALUATE_COMMAND = EVALUATE_COMMAND;
    /**
     * Quit command regex
     */
    public static final String REGEX_QUIT_COMMAND = QUIT_COMMAND;

    private CommandParser() {
        throw new IllegalStateException("Utility-class Constructor");
    }

    /**
     * Checks if command is valid
     *
     * @param input user input
     * @return the user input as a list
     * @throws SyntaxException command is invalid
     */
    public static List<String> checkCommand(String input) throws SyntaxException {
        if (input.length() == 0 || input.charAt(0) == SPACE_CHAR) throw new SyntaxException(Errors.INVALID_COMMAND);

        List<String> listOfInput = new ArrayList<>(Arrays.asList(input.split(SPACE_OR_SEMICOLON)));
        if (!input.matches(Commands.getCommand(listOfInput.get(0)).getCommandRegex()))
            throw new SyntaxException(Errors.WRONG_PARAMETER);

        return listOfInput;
    }

}
