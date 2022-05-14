package edu.norman.john.model.logic.card;

/**
 * Contains all possible values of a card (7-10, JQKA)
 *
 * @author kayak
 * @version 1.0
 */
public enum Value {
    SEVEN("7"),
    EIGHT("8"),
    NINE("9"),
    TEN("10"),
    JACK("J"),
    QUEEN("Q"),
    KING("K"),
    ACE("A");

    private final String stringValue;

    Value(String stringValue){
        this.stringValue = stringValue;
    }

    public String getStringValue() {
        return stringValue;
    }

    @Override
    public String toString(){
        return stringValue;
    }
}
