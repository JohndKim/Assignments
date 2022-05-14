package edu.norman.john.model.logic;

public class GlobalVariables {
    public static final int FIRST_ELEMENT = 0;
    public static final int NUM_OF_DECK_CARDS = 32;
    public static final int NUM_OF_PLAYERS = 4;
    public static final int STARTING_CARDS = 5;
    public static final int TOP_CARD = 0;
    public static final String STARTING_MESSAGE = "Player 1 takes the turn";
    public static final String INPUT_LINE = "> ";
    public static final String SPACE_SLASH_SPACE = " / ";
    public static final String COMMA = ",";
    public static final String PLAYER = "player";
    public static final String HAS_WON = "has won";
    public static final String DRAW = "Draw";

    private GlobalVariables() throws IllegalAccessException {
        throw new IllegalStateException("Utility-class Constructor");
    }
}
