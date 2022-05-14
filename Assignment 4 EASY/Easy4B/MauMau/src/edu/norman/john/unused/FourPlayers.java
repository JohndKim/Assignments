package edu.norman.john.unused;

import edu.norman.john.model.logic.Player;

import java.util.ArrayList;

public class FourPlayers {
    private static final int NUM_OF_PLAYERS = 4;
    private static final int STARTING_CARDS = 5;
    private final ArrayList<Player> players;
    private final Deck deck;
    private int totalCardsDistributed = 0;

    public FourPlayers(Deck deck){
        this.players = new ArrayList<>(NUM_OF_PLAYERS);
        this.deck = deck;
        distributeHand();
    }

    public void distributeHand(){
        for (Player player : players){
            for (int i = 0; i < STARTING_CARDS; i++) {
                player.addCardToHand(deck.getDeck().get(totalCardsDistributed));
                deck.getDeck().remove(0); // always remove top card
            }
        }
    }
}
