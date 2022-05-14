package edu.norman.john.errors;

/**
 * When a player attempts to play earlier than their turn
 *
 * @author kayak
 * @version 1.0
 */
public class WrongTurnException extends MauMauExceptions{
    private static final long serialVersionUID = 40235235005422654L;

    public WrongTurnException(String message){
        super(message);
    }
}