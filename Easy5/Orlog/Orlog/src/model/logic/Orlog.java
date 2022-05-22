package model.logic;

import errors.*;
import model.logic.player.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static commands.CommandParser.*;

/**
 * A class representing a game of Orlog
 *
 * @author kayak
 * @version 1.0
 */
public class Orlog {
    private final ArrayList<Player> players;
    private int turn;
    private boolean hasPlayed;

    /**
     * Initializes an Orlog game
     *
     * @param playerInfo player info for the players
     * @throws PlayerBuildException illegal player stats given
     */
    public Orlog(String playerInfo) throws PlayerBuildException {
        this.players = (ArrayList<Player>) initializePlayers(playerInfo);
        this.hasPlayed = false;
    }

    /**
     * Print command;
     * Prints the players' info
     *
     * @return info
     */
    public String print() {
        return players.get(0).getPlayerInfo() + NEW_LINE + players.get(1).getPlayerInfo();
    }

    /**
     * Roll command;
     * Rolls the dice for a player
     *
     * @param ids ids of the 6 dice sides
     * @return "okay"
     * @throws PlayerException 4+ GP gained
     * @throws SyntaxException wrong ID entered
     * @throws IllegalMoveException trying to roll again
     */
    public String roll(List<String> ids) throws SyntaxException, PlayerException, IllegalMoveException {
        thisTurnPlayer().calculateBattleElements(ids);
        if (players.get(0).getHasRolled() && players.get(1).getHasRolled()) calculateRolls();

        hasPlayed = true;
        return SUCCESS_WITH_MESSAGE;
    }

    /**
     * Turn command;
     * Switches turn
     *
     * @return next player's name
     * @throws PlayerException no player's turn
     * @throws IllegalMoveException attempting illegal turn (e.g. didn't roll)
     */
    public String turn() throws PlayerException, IllegalMoveException {
        if (!hasPlayed) throw new IllegalMoveException(Errors.HAS_NOT_PLAYED);

        turn++;
        hasPlayed = false;
        return thisTurnPlayer().getName().toString();
    }

    /**
     * Godfavor command;
     * Chooses a god favor for a player
     *
     * @param id god favor id
     * @param level god favor level
     * @return "okay"
     * @throws PlayerException no player
     * @throws SyntaxException non-existent id/level entered
     * @throws IllegalMoveException trying to choose multiple gods
     */
    public String godFavor(String id, int level) throws PlayerException, SyntaxException, IllegalMoveException {
        if (thisTurnPlayer().getHasChosenGodFavor()) throw new IllegalMoveException(Errors.ALREADY_CHOSEN);
        if (!thisTurnPlayer().getHasRolled()) throw new IllegalMoveException(Errors.HAS_NOT_ROLLED);
        if (thisTurnPlayer().getHasRolled() && hasPlayed) throw new IllegalMoveException(Errors.WAIT_YOUR_TURN);

        thisTurnPlayer().chooseGod(id, level);
        hasPlayed = true;
        return SUCCESS_WITH_MESSAGE;
    }

    /**
     * Evaluate command;
     * Evaluates both players after the rolls and god favors
     *
     * @return player stats and if anyone won
     * @throws IllegalMoveException illegal move attempted
     */
    public String evaluate() throws IllegalMoveException {
        Player p1 = players.get(0);
        Player p2 = players.get(1);

        // what evaluate command does in any phase but evaluate phase (also checks if they're alive)
        if (!p1.isAlive() || !p2.isAlive() || !p1.getHasRolled() || !p2.getHasRolled() || !p1.getHasChosenGodFavor()
                || !p2.getHasChosenGodFavor()) return print();
        // what evaluate command does in evaluate phase
        resetPlayerBooleans(p1, p2);
        playGodFavors(p1, p2); // replace
        if (checkWin().equals(SUCCESS_WITH_MESSAGE)) return print();
        return checkWin();
    }



    // ################ P R I V A T E #################


    /**
     * Calculates both players' rolls
     */
    private void calculateRolls() {
        attackPlayer(players.get(0), players.get(1));
        gainGodPower(players.get(0), players.get(1));
        stealGodPower(players.get(0), players.get(1));
    }

    /**
     * Each player attacks each other based on their rolls
     *
     * @param p1 player 1
     * @param p2 player 2
     */
    private void attackPlayer(Player p1, Player p2) {
        p2.takeAxeBowDamage(p1.getBattleElements().getAxeDamage(), p1.getBattleElements().getAxeDamage());
        p1.takeAxeBowDamage(p2.getBattleElements().getAxeDamage(), p2.getBattleElements().getBowDamage());
    }

    /**
     * Each player gains god power based on their rolls
     *
     * @param p1 player 1
     * @param p2 player 2
     */
    private void gainGodPower(Player p1, Player p2) {
        p1.changeGodPower(p1.getBattleElements().getGodPowerGained());
        p2.changeGodPower(p2.getBattleElements().getGodPowerGained());
    }

    /**
     * Calculates which player steals from who and increase/decreases their god power accordingly.
     * E.g. p1 steals 1 GP, but p2 steals 2GP, therefore p2 steals 1GP from p1
     *
     * @param p1 player 1
     * @param p2 player 2
     */
    private void stealGodPower(Player p1, Player p2) {
        int numToSteal = p1.getBattleElements().getGodPowerStolen() - p2.getBattleElements().getGodPowerStolen();
        // case p1 steals more from p2
        if (numToSteal > 0 && numToSteal > p2.getGodPower().toInt()) numToSteal = p2.getGodPower().toInt();
        // case p2 steals more from p1
        if (numToSteal < 0 && -numToSteal > p1.getGodPower().toInt()) numToSteal = -p1.getGodPower().toInt();

        p1.changeGodPower(numToSteal);
        p2.changeGodPower(-numToSteal);
    }

    /**
     * Play god favors
     *
     * @param p1 player 1
     * @param p2 player 2
     */
    private void playGodFavors(Player p1, Player p2) {
        if (p1.getGodFavor().getOrderPriority() < p2.getGodFavor().getOrderPriority()) playFavor(p1, p2);
        else playFavor(p2, p1);
    }

    /**
     * Executes both player's favor
     *
     * @param p1 player 1
     * @param p2 player 2
     */
    private void playFavor(Player p1, Player p2) {
        p1.getGodFavor().executeFavor(p1, p2);
        p2.getGodFavor().executeFavor(p2, p1);
    }

    /**
     * Initializes players
     *
     * @param playerInfo player info (from command line arguments)
     * @return arraylist of players
     * @throws PlayerBuildException illegal stats given
     */
    private List<Player> initializePlayers(String playerInfo) throws PlayerBuildException {
        ArrayList<String> infoList = new ArrayList<>(Arrays.asList(playerInfo.split(SEMICOLON)));
        if (Integer.parseInt(infoList.get(2)) < 5 || Integer.parseInt(infoList.get(3)) < 0 )
            throw new PlayerBuildException(Errors.INVALID_PLAYER_BUILD);

        ArrayList<Player> orlogPlayers = new ArrayList<>();
        orlogPlayers.add(new Player(new Name(infoList.get(0)), new ID(0), new Health(Integer.parseInt(infoList.get(2))),
                new GodPower(Integer.parseInt(infoList.get(3)))));
        orlogPlayers.add(new Player(new Name(infoList.get(1)), new ID(1), new Health(Integer.parseInt(infoList.get(2))),
                new GodPower(Integer.parseInt(infoList.get(3)))));

        return orlogPlayers;
    }

    /**
     * Finds the turn's player
     *
     * @return player
     * @throws PlayerException no such player exists
     */
    private Player thisTurnPlayer() throws PlayerException {
        int playerID = turn % 2;
        return players.stream().filter(p -> p.getId().toInt() == playerID)
                .findFirst().orElseThrow(() -> new PlayerException(Errors.NO_PLAYER_TURN));
    }

    /**
     * Resets both player's booleans
     *
     * @param p1 player 1
     * @param p2 player 2
     */
    private void resetPlayerBooleans(Player p1, Player p2) {
        p1.resetBooleans();
        p2.resetBooleans();
    }

    /**
     * Checks if a player has won
     *
     * @return winning message, draw, or "okay"
     */
    private String checkWin() {
        if (!players.get(0).isAlive() && !players.get(1).isAlive()) return DRAW;
        if (!players.get(0).isAlive()) return String.format(WIN_MESSAGE, players.get(1).getName());
        if (!players.get(1).isAlive()) return String.format(WIN_MESSAGE, players.get(0).getName());
        return SUCCESS_WITH_MESSAGE;
    }
}
