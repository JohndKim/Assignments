package run;

import commands.CommandParser;
import commands.Commands;
import commands.Result;
import core.Input;
import core.Output;
import errors.SyntaxException;
import model.logic.PostOffice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static commands.CommandParser.*;
/**
 * Represents a single session
 *
 * @author kayak
 * @version 1.0
 */
public class Session {
    private final Input input;
    private final Output output;
    private final Output errorOutput;
    private boolean isRunning;
    private final PostOffice postOffice;

    /**
     * Initializes the input, output, and error output
     *
     * @param input user input
     * @param output prints output
     * @param errorOutput prints error output
     */
    Session(Input input, Output output, Output errorOutput){
        this.input = input;
        this.output = output;
        this.errorOutput = errorOutput;
        this.postOffice = new PostOffice();
    }

    /**
     * Runs a session as long as isRunning is true
     */
    public void runSession(){
        isRunning = true;
        while (isRunning){
            processSingleCommand();
        }
    }

    /**
     * Processes a single command
     */
    public void processSingleCommand() {
        String userInput = input.read();
        ArrayList<String> userInputList = new ArrayList<>(Arrays.asList(userInput.split("[\\s+;]")));

        try {
            String command = CommandParser.checkCommand(userInputList, userInput);
            executeSingleCommand(command, userInputList);
        } catch (SyntaxException e) {
            errorOutput.output(e.getMessage());
        }
    }

    /**
     * Executes a single command
     *
     * @param command is the entire user input
     * @param userInput is the user input as an array list
     */
    public void executeSingleCommand(String command, List<String> userInput){
        Result result;

        try {
            result = Commands.getCommand(command).executeCommand(userInput, postOffice);
        } catch (SyntaxException e) {
            result = new Result(Result.ResultType.FAILURE, e.getMessage());
        }

        switch (result.getResultType()) {
            case SUCCESS:
                if (result.getResultMessage().equals(END_SESSION)) isRunning = false;
                else if (!result.getResultMessage().equals(SUCCESS_WITHOUT_MESSAGE)) output.output(result.getResultMessage());
                break;
            case FAILURE:
                errorOutput.output(result.getResultMessage());
                break;
            default:
                break;
        }
    }

}
