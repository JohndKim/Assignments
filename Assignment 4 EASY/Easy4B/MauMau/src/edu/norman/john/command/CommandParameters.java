package edu.norman.john.command;

/**
 * Contains all the commands and their parameters
 *
 * @author jean keem
 * @version 1.0
 */
public class CommandParameters {
    /**
     * Starting word for the game commands
     */
    public static final String START_COMMAND = "start";
    public static final String SHOW_COMMAND = "show";
    public static final String DISCARD_COMMAND = "discard";
    public static final String DRAW_COMMAND = "draw";
    public static final String QUIT_COMMAND = "quit";
    /**
     * Characters for commands
     */
    public static final String SPACE = " ";
    public static final String DASH = "-";
    public static final String GAME = "game";
    public static final String PLAYER_NUMBER = "[1234]";
    public static final String CARD_SUITS = "[EGHS]";
    public static final String CARD = "([\\d]{1,2}|[JQKA])" + CARD_SUITS; // JQKA = jack, queen, king, ace
    /**
     * Regex for the commands
     */
    public static final String REGEX_START_COMMAND = START_COMMAND + SPACE + "[\\d]+";
    public static final String REGEX_SHOW_GAME_COMMAND = SHOW_COMMAND + DASH + GAME;
    public static final String REGEX_SHOW_PLAYER_COMMAND = SHOW_COMMAND + SPACE + PLAYER_NUMBER;
    public static final String REGEX_DISCARD_COMMAND = DISCARD_COMMAND + SPACE + PLAYER_NUMBER + SPACE + CARD;
    public static final String REGEX_DRAW_COMMAND = DRAW_COMMAND + SPACE + PLAYER_NUMBER;

    public static final String REGEX_QUIT_COMMAND = QUIT_COMMAND;

    private CommandParameters() throws IllegalAccessException {
        throw new IllegalStateException("Utility-class Constructor");
    }
}
