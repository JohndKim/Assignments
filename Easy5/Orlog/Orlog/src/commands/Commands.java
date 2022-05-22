package commands;

import errors.Errors;
import errors.SemanticException;
import errors.SyntaxException;
import model.logic.Orlog;

import java.util.Arrays;
import java.util.List;

/**
 * An enum containing all executable commands
 *
 * @author kayak
 * @version 1.0
 */
public enum Commands {
    /**
     * print command (print)
     */
    PRINT(CommandParser.PRINT_COMMAND, CommandParser.REGEX_PRINT_COMMAND) {
        @Override
        public Result executeCommand(List<String> listOfInput, Orlog orlog) {
            String resultMessage = orlog.print();
            return new Result(Result.ResultType.SUCCESS, resultMessage);
        }
    },
    /**
     * Roll command (roll w1;w2;w3;w4;w5;w6)
     */
    ROLL(CommandParser.ROLL_COMMAND, CommandParser.REGEX_ROLL_COMMAND) {
        @Override
        public Result executeCommand(List<String> listOfInput, Orlog orlog) {
            String resultMessage;

            try {
                resultMessage = orlog.roll(Arrays.asList(listOfInput.get(1), listOfInput.get(2),
                        listOfInput.get(3), listOfInput.get(4), listOfInput.get(5), listOfInput.get(6)));
            } catch (SemanticException | SyntaxException e) {
                return new Result(Result.ResultType.FAILURE, e.getMessage());
            }

            return new Result(Result.ResultType.SUCCESS, resultMessage);
        }
    },
    /**
     * Turn command (turn)
     */
    TURN(CommandParser.TURN_COMMAND, CommandParser.REGEX_TURN_COMMAND) {
        @Override
        public Result executeCommand(List<String> listOfInput, Orlog orlog) {
            String resultMessage;

            try {
                resultMessage = orlog.turn();
            } catch (SemanticException e) {
                return new Result(Result.ResultType.FAILURE, e.getMessage());
            }

            return new Result(Result.ResultType.SUCCESS, resultMessage);
        }
    },
    /**
     * Choose god favor command (godfavor id;level)
     */
    CHOOSE_GOD_FAVOR(CommandParser.GOD_FAVOR_COMMAND, CommandParser.REGEX_GOD_FAVOR_COMMAND) {
        @Override
        public Result executeCommand(List<String> listOfInput, Orlog orlog) {
            String resultMessage;

            try {
                resultMessage = orlog.godFavor(listOfInput.get(1), Integer.parseInt(listOfInput.get(2)));
            } catch (SemanticException | SyntaxException e) {
                return new Result(Result.ResultType.FAILURE, e.getMessage());
            }

            return new Result(Result.ResultType.SUCCESS, resultMessage);
        }
    },
    /**
     * Evaluate command (evaluate)
     */
    EVALUATE(CommandParser.EVALUATE_COMMAND, CommandParser.REGEX_EVALUATE_COMMAND) {
        @Override
        public Result executeCommand(List<String> listOfInput, Orlog orlog) {
            String resultMessage;

            try {
                resultMessage = orlog.evaluate();
            } catch (SemanticException e) {
                return new Result(Result.ResultType.FAILURE, e.getMessage());
            }

            return new Result(Result.ResultType.SUCCESS, resultMessage);
        }
    },
    /**
     * quit command (quit)
     */
    QUIT(CommandParser.QUIT_COMMAND, CommandParser.REGEX_QUIT_COMMAND) {
        @Override
        public Result executeCommand(List<String> listOfInput, Orlog orlog) {
            return new Result(Result.ResultType.SUCCESS);
        }
    };

    private final String commandName;
    private final String commandRegex;

    Commands(String commandName, String commandRegex) {
        this.commandName = commandName;
        this.commandRegex = commandRegex;
    }

    /**
     * Gets the command name
     * @return command name
     */
    public String getCommandName() {
        return commandName;
    }

    /**
     * Gets the command regex
     * @return command regex
     */
    public String getCommandRegex() {
        return commandRegex;
    }

    /**
     * Gets the command with the same command name
     * @param commandName command name
     * @return command
     * @throws SyntaxException when there's no matching command name
     */
    public static Commands getCommand(String commandName) throws SyntaxException {
        for (Commands command : Commands.values()) {
            if (command.getCommandName().equals(commandName)) return command;
        }
        throw new SyntaxException(Errors.INVALID_COMMAND);
    }

    /**
     * An abstract method so each command executes different code
     *
     * @param listOfInput user input converted to an array list
     * @param orlog the current game
     * @return a result
     */
    public abstract Result executeCommand(List<String> listOfInput, Orlog orlog);
}
