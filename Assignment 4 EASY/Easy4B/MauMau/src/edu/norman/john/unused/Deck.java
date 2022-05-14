package edu.norman.john.unused;

import edu.norman.john.model.logic.card.Card;
import edu.norman.john.model.logic.card.Suit;
import edu.norman.john.model.logic.card.Value;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Deck {
    private static final int CARDS_IN_A_DECK = 32;
    private static final int NUM_OF_VALUES = 8;
    private static final int NUM_OF_SUITS = 4;
    private ArrayList<Card> deck;
    private final int seed;

    public Deck(int seed){
        this.seed = seed;
        this.deck = createOrderedDeck();
        shuffle();
    }


    /**
     * Creates an ordered deck
     *
     * @return the ordered deck
     */
    private ArrayList<Card> createOrderedDeck(){
        ArrayList<Card> orderedDeck = new ArrayList<>(CARDS_IN_A_DECK);
        for (Suit suit : Suit.values()){
            for (Value value : Value.values()){
                orderedDeck.add(new Card(value, suit));
            }
        }
        return orderedDeck;
    }

    public ArrayList<Card> shuffle(){
        Collections.shuffle(deck, new Random(seed));
        return deck;
    }

    public ArrayList<Card> getDeck() {
        return deck;
    }
}

// shuffling
// 4 players, each gets 5 cards, then a card is place up