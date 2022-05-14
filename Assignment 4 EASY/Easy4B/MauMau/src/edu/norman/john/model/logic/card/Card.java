package edu.norman.john.model.logic.card;

/**
 * Represents a card
 *
 * @author kayak
 * @version 1.0
 */
public class Card {
    private final Value value;
    private final Suit suit;
    private final String uniqueID;

    /**
     * Initializes the card's value and suit
     * @param value card value
     * @param suit card suit
     */
    public Card(Value value, Suit suit){
        this.value = value;
        this.suit = suit;
        this.uniqueID = value + suit.getInitialLetter();
    }

    public String getUniqueID() {
        return uniqueID;
    }

    public Suit getSuit() {
        return suit;
    }

    public Value getValue() {
        return value;
    }
}
