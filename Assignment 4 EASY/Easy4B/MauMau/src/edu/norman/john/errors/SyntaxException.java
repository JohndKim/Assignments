package edu.norman.john.errors;

/**
 * Exception when an invalid command is used or wrong parameters are entered
 *
 * @author kayak
 * @version 1.0
 */
public class SyntaxException extends MauMauExceptions{
    private static final long serialVersionUID = 86515235324123671L;

    public SyntaxException(String message){
        super(message);
    }
}
