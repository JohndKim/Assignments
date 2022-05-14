package edu.norman.john.model.logic.card;

/**
 * Contains all the possible suits (EGHS)
 *
 * @author kayak
 * @version 1.0
 */
public enum Suit {
    // Acorn
    EICHEL("E"),
    // Leaves
    GRUN("G"),
    // Hearts
    HERZ("H"),
    // Bells
    SCHELLLE("S");

    private final String initialLetter;

    Suit(String initialLetter){
        this.initialLetter = initialLetter;
    }

    public String getInitialLetter() {
        return initialLetter;
    }

    @Override
    public String toString(){
        return initialLetter;
    }
}