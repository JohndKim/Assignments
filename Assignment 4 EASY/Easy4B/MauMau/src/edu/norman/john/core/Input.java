package edu.norman.john.core;

import java.util.Scanner;

import static edu.norman.john.model.logic.GlobalVariables.INPUT_LINE;

/**
 * Takes in the user's input
 *
 * @author kayak
 * @version 1.0
 */
public class Input {
    public final String userInput;
    public final String[] userInputSplit;

    public Input(){
        this.userInput = findInput();
        this.userInputSplit = splitInput();
    }

    public String findInput(){
        System.out.print(INPUT_LINE);
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    /**
     * Splits input based on spaces (e.g. start <seed>: 0 = start, 1 = seed)
     * @return inputs
     */
    public String[] splitInput(){
        return userInput.split("\\s+");
    }

    public String[] getUserInputSplit() {
        return userInputSplit;
    }

    public String getUserInput() {
        return userInput;
    }

    @Override
    public String toString(){
        return userInput;
    }
}
