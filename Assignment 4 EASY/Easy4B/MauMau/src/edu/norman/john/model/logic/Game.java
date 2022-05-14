package edu.norman.john.model.logic;


import edu.norman.john.errors.Errors;
import edu.norman.john.errors.IllegalCardException;
import edu.norman.john.errors.WrongCardException;
import edu.norman.john.errors.WrongTurnException;
import edu.norman.john.model.logic.card.Card;
import edu.norman.john.model.logic.card.Suit;
import edu.norman.john.model.logic.card.Value;
import java.util.*;

import static edu.norman.john.command.CommandParameters.SPACE;
import static edu.norman.john.model.logic.GlobalVariables.*;

/**
 * A class representing a single game of Mau Mau
 *
 * @author kayak
 * @version 1.0
 */
public class Game {
    private int turn = 0;
    private boolean gameFinished = false;
    private ArrayList<Player> players;
    private final ArrayList<Card> unshuffledDeck;
    private ArrayList<Card> deck;
    private final Stack<Card> discardPile;

    /**
     * Initializes an unshuffled deck and the discard pile
     */
    public Game(){
        this.unshuffledDeck = createOrderedDeck();
        this.discardPile = new Stack<>();
    }

    /**
     * Method that carries out the "start game" command by creating the players, the deck, and deals the cards out to the players
     *
     * @param seed to randomize the cards dealt
     */
    public void startGame(int seed){
        this.players = initializePlayers();
        this.deck = createOrderedDeck();
        this.deck = shuffle(seed);
        deal();
        System.out.println(STARTING_MESSAGE);
    }

    /**
     * Shows the top card on the discard pile and number of cards left in the deck
     */
    public void showGame(){
        System.out.println(discardPile.peek().getUniqueID() + SPACE_SLASH_SPACE + deck.size());
    }

    /**
     * Shows all the cards in a player's hand
     *
     * @param playerNumber the player you want to see
     */
    public void showPlayerHand(int playerNumber){
        int index = playerNumber - 1;
        StringBuilder playerHand = stringHand(index);
        System.out.println(playerHand);
    }

    /**
     * Discards a card from the player
     *
     * @param playerNumber the player we want to discard a card from
     * @param discardCard the card to discard
     *
     * @throws WrongTurnException if a player tries to discard a card too soon or late (e.g. player 2's turn but player 4 tries to discard)
     * @throws WrongCardException the player doesn't have the card
     * @throws IllegalCardException this card is not a real card
     */
    public void discardCard(int playerNumber, String discardCard) throws WrongTurnException, WrongCardException, IllegalCardException {
        int index = playerNumber - 1;
        String lastCard = discardPile.peek().getUniqueID();

        if (isValidPlay(discardCard)){
            if (index != turn) throw new WrongTurnException(Errors.WRONG_TURN);
            removeCard(playerNumber, discardCard);
            changeTurn();
            checkWin(playerNumber);
        } else throw new IllegalCardException(String.format(Errors.ILLEGAL_CARD, discardCard, lastCard));
    }

    /**
     * Adds a card to a player's hand
     *
     * @param playerNumber player we want to give a card
     */
    public void draw(int playerNumber) throws WrongTurnException {
        int index = playerNumber - 1;
        checkTurn(index);
        drawCard(players.get(index));
        checkDraw();
    }

    // SUPPLEMENTAL METHODS

    /**
     * Creates an ordered deck
     *
     * @return ordered deck
     */
    ArrayList<Card> createOrderedDeck(){
        ArrayList<Card> orderedDeck = new ArrayList<>(NUM_OF_DECK_CARDS);
        for (Suit suit : Suit.values()){
            for (Value value : Value.values()){
                orderedDeck.add(new Card(value, suit));
            }
        }
        return orderedDeck;
    }

    /**
     * Shuffles the deck
     *
     * @param seed a num to shuffle
     * @return a shuffled deck
     */
    public ArrayList<Card> shuffle(int seed){
        Collections.shuffle(deck, new Random(seed));
        return deck;
    }

    /**
     * Distributes 5 cards to each player from the shuffled deck
     */
    public void deal(){
        for (Player player : players){
            for (int i = 0; i < STARTING_CARDS; i++) {
                drawCard(player);
            }
        }
        discardPile.add(deck.get(TOP_CARD));
        deck.remove(TOP_CARD);
    }

    /**
     * Adds a card to the player's hand
     *
     * @param player hand
     */
    public void drawCard(Player player){
        player.addCardToHand(deck.get(TOP_CARD));
        deck.remove(TOP_CARD); // always remove top card
    }

    /**
     * Creates four players
     *
     * @return the four players
     */
    public ArrayList<Player> initializePlayers(){
        ArrayList<Player> mauMauPlayers = new ArrayList<>(NUM_OF_PLAYERS);
        for (int i = 0; i < NUM_OF_PLAYERS; i++) mauMauPlayers.add(new Player());
        return mauMauPlayers;
    }

    /**
     * Converts the Card array list into a String builder for a player's hand
     *
     * @param index player's index (array list)
     * @return the String builder
     */
    public StringBuilder stringHand(int index){
        StringBuilder playerHand = new StringBuilder();
        ArrayList<Card> hand = players.get(index).getHand();

        for (Card card : hand) {
            playerHand.append(card.getUniqueID()).append(COMMA);
        }
        playerHand.deleteCharAt(playerHand.length() - 1);
        return playerHand;
    }

    /**
     * Method to calculate if the card being discarded follows the game's rules (same suit or value)
     *
     * @param discardCard card to discard (its ID)
     * @return true = valid; false = invalid
     */
    public boolean isValidPlay(String discardCard) {
        Card discardedCard = null;
        for (Card card : unshuffledDeck) {
            if (card.getUniqueID().equals(discardCard)){
                discardedCard = card;
                break;
            }
        }
        if (discardedCard == null) return false;
        Card lastCard = discardPile.peek();
        return (discardedCard.getValue() == lastCard.getValue() || discardedCard.getSuit() == lastCard.getSuit());
    }

    /**
     * Removes a card from a player's hand
     *
     * @param playerNumber player's hand
     * @param discardCard card to remove
     * @throws WrongCardException doesn't have the card
     */
    public void removeCard(int playerNumber, String discardCard) throws WrongCardException{
        int index = playerNumber - 1;

        for (Card card : players.get(index).getHand()){
            if (card.getUniqueID().equals(discardCard)) {
                discardPile.add(card);
                players.get(index).getHand().remove(card); // card exists
                return;
            }
        }
        throw new WrongCardException(Errors.WRONG_CARD);
    }

    /**
     * checks if anyone has won (no cards in their hand)
     * @param playerNumber player to check
     */
    public void checkWin(int playerNumber){
        int index = playerNumber - 1;
        if (players.get(index).getHand().isEmpty()) {
            gameFinished = true;
            System.out.println(PLAYER + SPACE + playerNumber + SPACE + HAS_WON);
        }
    }

    /**
     * Checks if it is a draw (no cards left in the deck)
     */
    public void checkDraw(){
        if (deck.isEmpty()) {
            gameFinished = true;
            System.out.println(DRAW);
        }
    }

    /**
     * Changes the turn
     */
    public void changeTurn(){
        if (turn == 3) turn = 0;
        else turn++;
    }

    /**
     * Checks if the game is being played in order (1 -> 2 -> 3 -> 4 -> 1)
     * @param index the player
     * @throws WrongTurnException the player is not playing in order of turns
     */
    public void checkTurn(int index) throws WrongTurnException {
        if (index != turn) throw new WrongTurnException(Errors.WRONG_TURN);
        changeTurn();
    }

    /**
     * Gets if the game is finished or not
     *
     * @return false = continuing, true = finished
     */
    public boolean getGameFinished(){
        return gameFinished;
    }
}
