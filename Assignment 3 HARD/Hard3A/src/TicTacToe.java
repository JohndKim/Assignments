/**
 * This class represents a tictactoe game
 *
 * @author kyae
 * @version 1.0
 */

public class TicTacToe {
    static String[][] board = new String[3][3];
    private String finishedBoard = "";

    public static void main(String[] args){
        TicTacToe tic = new TicTacToe();
        tic.startGame(args);
        String gameBoard = tic.toString();
        System.out.println(gameBoard);
    }

    /**
     * This method starts the game and places each symbol into the 2d array
     *
     * @param args command arguments detailing which square to place the X or O
     */
    public void startGame(String[] args){
        boolean win = false;

        // replaces null with "" so i dont get the damn nullpointerexception error T-T
        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
                board[i][j] = "";
            }
        }

        for (int i = 0; i < args.length; i++){
            // X: even: 0, 2, 4, 6, 8
            int square = Integer.parseInt(args[i]);
            if (i % 2 == 0){
                if (square < 3) board[0][square] = "X";
                else if (square >= 3 && square < 6) board[1][square-3] = "X";
                else if (square >= 6 && square < 9) board[2][square-6] = "X";
                else System.out.println("Invalid number.");
                // prints winning player and turn
                if (wonGame("X") && win == false) {
                    System.out.println("Player 1 has won on turn " + (i+1));
                    win = true;
                }
            } else {
                // O: odd: 1, 3, 5, 7
                if (square < 3) board[0][square] = "O";
                else if (square >= 3 && square < 6) board[1][square-3] = "O";
                else if (square >= 6 && square < 9) board[2][square-6] = "O";
                else System.out.println("Invalid number.");
                // prints winning player and turn
                if (wonGame("O") && win == false) {
                    System.out.println("Player 2 has won on turn " + (i+1));
                    win = true;
                }
            }
        }
    }

    /**
     * A method to see who wins the game
     *
     * @param player is X or O
     * @return true = win, false = no win
     */
    public boolean wonGame(String player){
        // checks for row wins
        for (int i = 0; i < 3; i++){
            if (board[i][0].equals(player) && board[i][1].equals(player) && board[i][2].equals(player)) return true;
        }
        // checks for column wins
        for (int i = 0; i < 3; i++){
            if (board[0][i].equals(player) && board[1][i].equals(player) && board[2][i].equals(player)) return true;
        }
        // checks for diagonal top left to bottom right
        if (board[0][0].equals(player) && board[1][1].equals(player) && board[2][2].equals(player)) return true;
        // checks for diagonal top right to bottom left
        if (board[0][2].equals(player) && board[1][1].equals(player) && board[2][0].equals(player)) return true;
        return false;
    }

    /**
     * This method converts the 2d array into a printable string
     *
     * @return the board
     */
    @Override
    public String toString(){
        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
                finishedBoard += board[i][j] + " ";
            }
            finishedBoard += "\n";
        }
        return finishedBoard;
    }
}
