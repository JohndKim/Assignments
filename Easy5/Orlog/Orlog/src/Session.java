import commands.CommandParser;
import commands.Commands;
import commands.Result;
import core.Input;
import core.Output;
import errors.PlayerBuildException;
import errors.SyntaxException;
import model.logic.Orlog;

import java.util.List;

/**
 * Class representing a session of an Orlog game
 *
 * @author kayak
 * @version 1.0
 */
public class Session {
    private final Input input;
    private final Output output;
    private final Output errorOutput;
    private final Orlog orlog;
    private boolean isRunning;

    /**
     * Initializes input, output, error output, and player info
     *
     * @param input input
     * @param output output
     * @param errorOutput error output
     * @param playerInfo player info
     * @throws PlayerBuildException illegal player stats given
     */
    public Session(Input input, Output output, Output errorOutput, String playerInfo) throws PlayerBuildException {
        this.input = input;

        this.output = output;
        this.errorOutput = errorOutput;
        this.orlog = new Orlog(playerInfo);
    }

    /**
     * Runs a session
     */
    public void runSession() {
        isRunning = true;
        while (isRunning) {
            processSingleCommand();
        }
    }

    /**
     * Processes a single command
     */
    private void processSingleCommand() {
        String userInput = input.read();

        try {
            List<String> listOfInput = CommandParser.checkCommand(userInput);
            executeSingleCommand(listOfInput.get(0), listOfInput);
        } catch (SyntaxException e) {
            errorOutput.output(e.getMessage());
        }
    }

    /**
     * Executes a single command
     *
     * @param commandName name of command
     * @param listOfInput input converted to a list
     */
    private void executeSingleCommand(String commandName, List<String> listOfInput) {
        Result result;

        try {
            result = Commands.getCommand(commandName).executeCommand(listOfInput, orlog);
        } catch (SyntaxException e) {
            result = new Result(Result.ResultType.FAILURE, e.getMessage());
        }

        switch (result.getResultType()) {
            case SUCCESS:
                if (result.getResultMessage() == null) isRunning = false;
                else if (!result.getResultMessage().equals(CommandParser.SUCCESS_WITHOUT_MESSAGE))
                    output.output(result.getResultMessage());
                break;
            case FAILURE:
                errorOutput.output(result.getResultMessage());
                break;
            default:
                break;
        }
    }
}
