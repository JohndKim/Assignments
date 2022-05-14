package edu.norman.john.command;

import edu.norman.john.errors.*;
import edu.norman.john.model.logic.Game;

/**
 * Contains all the commands in the game
 *
 * @author kayak
 * @version 1.0
 */
public enum GameCommands {

    START_GAME(CommandParameters.START_COMMAND, CommandParameters.REGEX_START_COMMAND){ // start <seed>
        @Override
        public void executeCommand(String[] input, Game game){
            game.startGame(Integer.parseInt(input[1]));
        }
    },
    SHOW_GAME("show-game", CommandParameters.REGEX_SHOW_GAME_COMMAND){ // show-game
        @Override
        public void executeCommand(String[] input, Game game){
            game.showGame();
        }
    },
    SHOW_PLAYER("show", CommandParameters.REGEX_SHOW_PLAYER_COMMAND){ // show <num>
        @Override
        public void executeCommand(String[] input, Game game) {
            game.showPlayerHand(Integer.parseInt(input[1]));
        }
    },
    DISCARD_CARD(CommandParameters.DISCARD_COMMAND, CommandParameters.REGEX_DISCARD_COMMAND){
        @Override
        public void executeCommand(String[] input, Game game) throws WrongTurnException, WrongCardException, IllegalCardException {
            game.discardCard(Integer.parseInt(input[1]), input[2]);
        }
    },
    DRAW_CARD(CommandParameters.DRAW_COMMAND, CommandParameters.REGEX_DRAW_COMMAND){ //draw <num>
        @Override
        public void executeCommand(String[] input, Game game) throws WrongTurnException {
            game.draw(Integer.parseInt(input[1]));
        }
    };

    private final String commandName;
    private final String commandRegex;
    GameCommands(String commandName, String commandRegex){
     this.commandName = commandName;
     this.commandRegex = commandRegex;
    }

    public String getCommandName() {
        return commandName;
    }

    public String getCommandRegex() {
        return commandRegex;
    }

    public static GameCommands getCommand(String commandName) throws SyntaxException {
        for (GameCommands command : GameCommands.values()){
            if (command.getCommandName().equalsIgnoreCase(commandName)) return command;
        }
        throw new SyntaxException(Errors.INVALID_COMMAND);
    }

    public abstract void executeCommand(String[] input, Game game) throws WrongTurnException, WrongCardException, IllegalCardException;
}
