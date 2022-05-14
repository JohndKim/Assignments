package edu.norman.john.model.logic;

import edu.norman.john.model.logic.card.Card;

import java.util.ArrayList;

public class Player {
    private final ArrayList<Card> hand;

    public Player(){
        this.hand = new ArrayList<>();
    }

    public void addCardToHand(Card card){
        hand.add(card);
    }

    public ArrayList<Card> getHand() {
        return hand;
    }


}
