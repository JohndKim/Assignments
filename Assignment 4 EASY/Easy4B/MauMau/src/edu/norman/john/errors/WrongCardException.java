package edu.norman.john.errors;

/**
 * When trying to discard a card you don't have
 *
 * @author kayak
 * @version 1.0
 */
public class WrongCardException extends MauMauExceptions{
    private static final long serialVersionUID = 777652352341209834L;

    public WrongCardException(String message){
        super(message);
    }
}
