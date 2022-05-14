package edu.norman.john.errors;

/**
 * When trying to discard a card that doesn't align with the card-discarding rules
 *
 * @author kayak
 * @version 1.0
 */
public class IllegalCardException extends MauMauExceptions{
    private static final long serialVersionUID = 8467846946946814L;

    public IllegalCardException(String message){
        super(message);
    }
}
