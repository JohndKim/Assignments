import core.Input;
import core.Output;
import errors.PlayerBuildException;

import java.util.Scanner;

/**
 *
 * @author kayak
 * @version 1.0
 */
public class Application {
    private static final Input INPUT = () -> new Scanner(System.in).nextLine();
    private static final Output OUTPUT = System.out::println;
    private static final String ERROR = "Error: ";
    private static final Output ERROR_OUTPUT = message -> System.out.println(ERROR + message);

    /**
     * Main method that runs a session
     *
     * @param args args (contains player info)
     */
    public static void main(String[] args) throws PlayerBuildException {
        var session = new Session(INPUT, OUTPUT, ERROR_OUTPUT, args[0]);
        session.runSession();
    }
}
