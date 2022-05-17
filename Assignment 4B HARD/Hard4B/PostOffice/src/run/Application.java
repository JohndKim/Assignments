package run;

import core.Input;
import core.Output;

import java.util.Scanner;

/**
 * Runs the entire program
 *
 * @author kayak
 * @version 1.0
 */
public class Application {

    private static final Input INPUT = () -> new Scanner(System.in).nextLine();
    private static final Output OUTPUT = System.out::println;
    // :: is a method referencer (similar to ->), A::B is B references method from A,
    // hence println is just a method from System.out
    private static final String ERROR = "Error: ";
    private static final Output ERROR_OUTPUT = message -> System.out.println(ERROR + message);
    // errorOutput.output(message), message is the error message,
    // used as an argument for this method

    /**
     * Main method, runs the program
     *
     * @param args args
     */
    public static void main(String[] args) {
        var session = new Session(INPUT, OUTPUT, ERROR_OUTPUT);
        session.runSession();
    }
}
