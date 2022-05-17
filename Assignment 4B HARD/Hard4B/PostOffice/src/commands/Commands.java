package commands;

import errors.Errors;
import errors.LogException;
import errors.SemanticsException;
import errors.SyntaxException;
import model.logic.PostOffice;
import model.logic.mail.PostalServices;
import model.logic.user.*;

import java.util.List;

import static commands.CommandParser.*;

/**
 * A class that executes the post office commands
 *
 * @author kayak
 * @version 1.0
 */
public enum Commands {
    /**
     * The add-customer command
     */
    ADD_CUSTOMER(CommandParser.ADD_CUSTOMER_COMMAND, CommandParser.REGEX_ADD_CUSTOMER_COMMAND){
        @Override
        public Result executeCommand(List<String> listInput, PostOffice postOffice) {
            String resultMessage;

            try {
                resultMessage = postOffice.addCustomer(new Customer(new ID(listInput.get(5)), new Name(listInput.get(1), listInput.get(2)), new Password(listInput.get(4)), new Username(listInput.get(3))));
            } catch (SemanticsException e) {
                return new Result(Result.ResultType.FAILURE, e.getMessage());
            }

            return new Result(Result.ResultType.SUCCESS, resultMessage);
        }
    },
    /**
     * The add-mailman command
     */
    ADD_MAILMAN(CommandParser.ADD_MAILMAN_COMMAND, CommandParser.REGEX_ADD_MAILMAN_COMMAND){
        @Override
        public Result executeCommand(List<String> listInput, PostOffice postOffice) {
            String resultMessage;

            try {
                resultMessage = postOffice.addMailman(new Mailman(new ID(listInput.get(3)), new Name(listInput.get(1), listInput.get(2)), new Password(listInput.get(4))));
            } catch (SemanticsException e){
                return new Result(Result.ResultType.FAILURE, e.getMessage());
            }

            return new Result(Result.ResultType.SUCCESS, resultMessage);
        }
    },
    /**
     * The add-agent command
     */
    ADD_AGENT(CommandParser.ADD_AGENT_COMMAND, CommandParser.REGEX_ADD_AGENT_COMMAND){
        @Override
        public Result executeCommand(List<String> listInput, PostOffice postOffice) {
            String resultMessage;

            try {
                resultMessage = postOffice.addAgent(new Agent(new ID(listInput.get(3)), new Name(listInput.get(1), listInput.get(2)), new Password(listInput.get(4))));
            } catch (SemanticsException e){
                return new Result(Result.ResultType.FAILURE, e.getMessage());
            }

            return new Result(Result.ResultType.SUCCESS, resultMessage);
        }
    },
    /**
     * The authenticate command
     */
    AUTHENTICATE(CommandParser.AUTHENTICATE_COMMAND, CommandParser.REGEX_AUTHENTICATE_COMMAND){
        @Override
        public Result executeCommand(List<String> listInput, PostOffice postOffice) {
            String resultMessage;

            try {
                resultMessage = postOffice.authenticate(listInput.get(1), listInput.get(2));
            } catch (LogException e) {
                return new Result(Result.ResultType.FAILURE, e.getMessage());
            }

            return new Result(Result.ResultType.SUCCESS, resultMessage);
        }
    },
    /**
     * The logout command
     */
    LOGOUT(CommandParser.LOGOUT_COMMAND, CommandParser.REGEX_LOGOUT_COMMAND){
        @Override
        public Result executeCommand(List<String> listInput, PostOffice postOffice) {
            String resultMessage;

            try {
                resultMessage = postOffice.logout();
            } catch (LogException e) {
                return new Result(Result.ResultType.FAILURE, e.getMessage());
            }

            return new Result(Result.ResultType.SUCCESS, resultMessage);
        }
    },
    /**
     * The send-mail command
     */
    SEND_MAIL(CommandParser.SEND_MAIL_COMMAND, CommandParser.REGEX_SEND_MAIL_COMMAND){
        @Override
        public Result executeCommand(List<String> listInput, PostOffice postOffice) {
            String resultMessage;
            String command = convertToString(listInput);

            try {
                if (command.matches(REGEX_SEND_MAIL_CUSTOMER_COMMAND)){
                    resultMessage = postOffice.sendMailCustomer(PostalServices.getPostalService(listInput.get(1)), new Username(listInput.get(2)));
                } else {
                    resultMessage = postOffice.sendMailMailman(PostalServices.getPostalService(listInput.get(1)), new Username(listInput.get(2)), new Username(listInput.get(3)));
                }
            } catch (SemanticsException | SyntaxException e) {
                return new Result(Result.ResultType.FAILURE, e.getMessage());
            }

            return new Result(Result.ResultType.SUCCESS, resultMessage);
        }
    },
    /**
     * The get-mail command
     */
    GET_MAIL(GET_MAIL_COMMAND, REGEX_GET_MAIL_COMMAND){
        @Override
        public Result executeCommand(List<String> listInput, PostOffice postOffice) {
            String resultMessage;
            String command = convertToString(listInput);

            try {
                if (command.matches(REGEX_GET_MAIL_CUSTOMER_COMMAND)){
                    resultMessage = postOffice.getMailCustomer();
                } else {
                    resultMessage = postOffice.getMailMailman(new Username(listInput.get(1)));
                }
            } catch (SemanticsException e){
                return new Result(Result.ResultType.FAILURE, e.getMessage());
            }

            return new Result(Result.ResultType.SUCCESS, resultMessage);
        }
    },
    /**
     * The list-mail command
     */
    LIST_MAIL(LIST_MAIL_COMMAND, REGEX_LIST_MAIL_COMMAND){
        @Override
        public Result executeCommand(List<String> listInput, PostOffice postOffice) {
            String resultMessage;
            String command = convertToString(listInput);

            try {
                if (command.matches(REGEX_LIST_MAIL_CUSTOMER_COMMAND)) resultMessage = postOffice.listMailCustomer();
                else if (command.matches(REGEX_LIST_MAIL_MAILMAN_AGENT_COMMAND)) resultMessage = postOffice.listMailMailManAgent(new Username(listInput.get(1)));
                else return new Result(Result.ResultType.FAILURE, Errors.WRONG_PARAMETERS);
            } catch (SemanticsException e){
                return new Result(Result.ResultType.FAILURE, e.getMessage());
            }

            return new Result(Result.ResultType.SUCCESS, resultMessage);
        }
    },
    /**
     * The list-price command
     */
    LIST_PRICE(LIST_PRICE_COMMAND, REGEX_LIST_PRICE_COMMAND){
        @Override
        public Result executeCommand(List<String> listInput, PostOffice postOffice) {
            String resultMessage;
            String command = convertToString(listInput);

            try {
                if (command.matches(REGEX_LIST_PRICE_CUSTOMER_COMMAND)) resultMessage = postOffice.listPriceCustomer();
                else if (command.matches(REGEX_LIST_PRICE_MAILMAN_AGENT_COMMAND)) resultMessage = postOffice.listPriceMailmanAgent(new Username(listInput.get(1)));
                else return new Result(Result.ResultType.FAILURE, Errors.WRONG_PARAMETERS);
            } catch (SemanticsException | SyntaxException e){
                return new Result(Result.ResultType.FAILURE, e.getMessage());
            }

            return new Result(Result.ResultType.SUCCESS, resultMessage);
        }
    },
    /**
     * The reset-pin command
     */
    RESET_PIN(RESET_PIN_COMMAND, REGEX_RESET_PIN_COMMAND){
        @Override
        public Result executeCommand(List<String> listInput, PostOffice postOffice) {
            String resultMessage;

            try {
                resultMessage = postOffice.resetPassword(new Username(listInput.get(1)), new Password(listInput.get(2)));
            } catch (SemanticsException e){
                return new Result(Result.ResultType.FAILURE, e.getMessage());
            }

            return new Result(Result.ResultType.SUCCESS, resultMessage);
        }
    },
    /**
     * The quit command
     */
    QUIT (CommandParser.QUIT_COMMAND, CommandParser.REGEX_QUIT_COMMAND){
        @Override
        public Result executeCommand(List<String> inputList, PostOffice postOffice) {
            return new Result(Result.ResultType.SUCCESS, END_SESSION);
        }
    };

    private final String commandName;
    private final String commandRegex;

    Commands(String commandName, String commandRegex){
        this.commandName = commandName;
        this.commandRegex = commandRegex;
    }

    public String getCommandName() {
        return commandName;
    }

    public String getCommandRegex() {
        return commandRegex;
    }

    /**
     * Returns the Commands command that matches its command name
     *
     * @param commandName name of command to return
     * @return command
     * @throws SyntaxException invalid command name entered
     */
    public static Commands getCommand(String commandName) throws SyntaxException {
        for (Commands command : Commands.values()){
            if (command.getCommandName().equals(commandName)) return command;
        }
        throw new SyntaxException(Errors.INVALID_COMMAND);
    }

    /**
     * Converts a list into string (user input list into one whole input)
     *
     * @param listInput list of user input
     * @return String of user input
     */
    private static String convertToString(List<String> listInput){
        StringBuilder command = new StringBuilder();
        for (int i = 0; i < listInput.size(); i++){
            if (i == 0) command.append(listInput.get(i)).append(SPACE);
            else command.append(listInput.get(i)).append(SEMI_COLON);
        }
        command.deleteCharAt(command.length()-1);
        return command.toString();
    }

    /**
     * Executes a command
     *
     * @param listInput the user input
     * @param postOffice the post office
     * @return a result
     */
    public abstract Result executeCommand(List<String> listInput, PostOffice postOffice);
}
