package edu.norman.john;

import edu.norman.john.core.Input;
import edu.norman.john.errors.Errors;
import edu.norman.john.errors.MauMauExceptions;
import edu.norman.john.errors.SyntaxException;
import edu.norman.john.model.logic.Game;
import edu.norman.john.command.GameCommands;

import static edu.norman.john.command.CommandParameters.QUIT_COMMAND;
import static edu.norman.john.command.CommandParameters.SPACE;
import static edu.norman.john.model.logic.GlobalVariables.FIRST_ELEMENT;

/**
 * Represents a single session of a game
 *
 * @author kayak
 * @version 1.0
 */
public class Session {
    private Input input;
    private boolean isPlaying;
    private final Game game;

    /**
     * Initializes a new game
     */
    Session(){
        this.game = new Game();
    }

    /**
     * A method to begin a session
     */
    public void playSession(){
        isPlaying = true;
        while (isPlaying){
            processSingleCommand();
        }
    }

    /**
     * A method to get user input and check if the command is valid and run it
     */
    public void processSingleCommand(){
        if (game.getGameFinished()) isPlaying = false;
        this.input = new Input();
        String command = input.getUserInput();
        String[] fullInput = input.getUserInputSplit();
        executeSingleCommand(command, fullInput);
    }

    /**
     * Actually runs the command
     *
     * @param command command name
     * @param fullInput the input as a String array
     */
    public void executeSingleCommand(String command, String[] fullInput){
        try {
            basicCheck(command);
            String commandName = fullInput[FIRST_ELEMENT];
            checkCommand(commandName, command);

            if (commandName.equalsIgnoreCase(QUIT_COMMAND)) isPlaying = false;
            else GameCommands.getCommand(commandName).executeCommand(fullInput, game);
        } catch (MauMauExceptions e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Checks if command is valid
     *
     * @param commandName name of command
     * @param input user input
     * @throws SyntaxException if command is invalid
     */
    public void checkCommand(String commandName, String input) throws SyntaxException {
        if (!input.matches(GameCommands.getCommand(commandName).getCommandRegex())) throw new SyntaxException(Errors.ILLEGAL_COMMAND_USE);
    }

    /**
     * Checks if command is empty
     *
     * @param command entire command
     * @throws SyntaxException if it is a space or empty
     */
    public void basicCheck(String command) throws SyntaxException{
        if (command.equals(SPACE) || command.isEmpty()) throw new SyntaxException(Errors.INVALID_COMMAND);
    }

}
