//package edu.norman.john.model.logic;
//
//import edu.norman.john.model.logic.card.Card;
//import edu.norman.john.model.logic.card.Suit;
//import edu.norman.john.model.logic.card.Value;
//
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.Objects;
//import java.util.Random;
//
//public class SupplementalGameMethods {
//    private static final String STARTING_MESSAGE = "Player 1 takes the turn";
//    private static final int NUM_OF_DECK_CARDS = 32;
//    private static final int NUM_OF_PLAYERS = 4;
//    private static final int STARTING_CARDS = 5;
//    private static final int TOP_CARD = 0;
//    private static final String SPACE_SLASH_SPACE = " / ";
//    private static final String COMMA = ",";
//    ArrayList<Card> createOrderedDeck(){
//        ArrayList<Card> orderedDeck = new ArrayList<>(NUM_OF_DECK_CARDS);
//        for (Suit suit : Suit.values()){
//            for (Value value : Value.values()){
//                orderedDeck.add(new Card(value, suit));
//            }
//        }
//        return orderedDeck;
//    }
//
//    public ArrayList<Card> shuffle(int seed){
//        Collections.shuffle(deck, new Random(seed));
//        return deck;
//    }
//
//    public void distributeHand(){
//        for (Player player : players){
//            for (int i = 0; i < STARTING_CARDS; i++) {
//                drawCard(player);
//            }
//        }
//        discardPile.add(deck.get(TOP_CARD));
//        deck.remove(TOP_CARD);
//    }
//
//    public void drawCard(Player player){
//        player.addCardToHand(deck.get(TOP_CARD));
//        deck.remove(TOP_CARD); // always remove top card
//    }
//
//    public ArrayList<Player> initializePlayers(){
//        ArrayList<Player> mauMauPlayers = new ArrayList<>(NUM_OF_PLAYERS);
//        for (int i = 0; i < NUM_OF_PLAYERS; i++) mauMauPlayers.add(new Player());
//        return mauMauPlayers;
//    }
//
//    public boolean isValidPlay(String discardCard){
//        String value = discardCard.substring(0,1);
//        String suit = discardCard.substring(1,2);
//
//        String lastCard = discardPile.get(TOP_CARD).getUniqueID();
//        String lastValue = lastCard.substring(0,1);
//        String lastSuit = lastCard.substring(1,2);
//
//        if (value.equals(lastValue) || suit.equals(lastSuit)) {
//            return true;
//        }
//        return false;
//    }
//
//    public void removeCard(int playerNumber, String discardCard){
//        int index = playerNumber - 1;
//
//        for (Card card : players.get(index).getHand()){
//            if (card == null) return; // end of hand
//            if (Objects.equals(card.getUniqueID(), discardCard)) {
//                discardPile.add(card);
//                players.get(index).getHand().remove(card); // card exists
//            }
//        }
//    }
//}
