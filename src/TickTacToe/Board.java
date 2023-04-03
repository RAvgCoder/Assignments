package TickTacToe;

import java.util.Arrays;

public class Board {
    private final Player[][] board;    // Creates a board to play on
    private final ScoreBoard scoreBoard = new ScoreBoard();
    private final Player dot = new Player(".",false);  // Used to fill the board with dots
    private int spaceLeft;    // Space left on the board when playing
    private int[] totalMoves = new int[2];   // Move count of the players
    private int currPlayer =0;

    /**
     * Constructor for the board object
     * @param size size of the board to be created
     */
    public Board(int size)
    {
        board = new Player[size][size];
        bordReset(3);
    }

    /**
     * Places a player on the board if no one is currently there
     * @param player current player
     * @param x Position on the board
     * @param y Position on the board
     * @return true if successful false, if it's not
     */
    public boolean placePlayer(Player player, int x, int y)
    {
        if (board[x][y]!=dot)
            return false;

        board[x][y] = player;
        spaceLeft--;
        totalMoves[currPlayer]++;
        currPlayer++;
        currPlayer=currPlayer%2;
        return true;
    }

    /**
     * Checks if a player has won
     * @return True if player won
     */
    public boolean isWinner()
    {
        return checkDiagonal(0)|| checkHorizontalAndVertical();
    }

       /**
     * Returns true if there's still space on the board.
     * @return True if there's on space on the board.
     */
    public boolean noSpaceOnBoard(){return spaceLeft==0;}

    /**
     * Prints the scoreBoard for all match plaid.
     */
    public void showScoreBoard(){System.out.println(scoreBoard);}

    public void bordReset(int size)
    {
        spaceLeft = size*size;
        for (int i = 0; i < size; i++){   // Fill the array with dots
            Arrays.fill(board[i], dot);
        }
        currPlayer=0;
        totalMoves = new int[2];
    }
     /**
     * Checks both horizontal and vertical if any player won the game
     * @return True if winner is found and False if not.
     */
    private boolean checkHorizontalAndVertical()
    {
        // row = {x, y}
        int[] row = new int[2];

        // col = {a | b | c} -> sums up each col if any is 3 then the player has won
        int[] colX = new int[3];
        int[] colY = new int[3];

        for (int i = 0; i<3; i++) {
            Player player = null;
            for (int j = 0; j < 3; j++) {
                player = board[i][j];
                if (player.getPlayerSymbol().equals("X")){
                    row[0]++;
                    colX[j]++;
                }else if (player.getPlayerSymbol().equals("O")){
                    row[1]++;
                    colY[j]++;
                }
                // Checks if player has won on that row
                if (row[0]==3||row[1]==3){
                    updateScore(player);
                    return true;
                }
            }
            // Clears the row to start recording again
            row = new int[3];
        }

        // Checks if player won on a particular column
        for (int i=0; i<3; i++){
            if (colX[i]==3){
                updateScore(board[0][i]);
                return true;
            }
            else if (colY[i]==3){
                updateScore(board[0][i]);
                return true;
            }
        }
        // If no winner found
        return false;
    }

    /**
     * Checks diagonal position for any player that won
     * @param start The (row,col) direction you start at moving down diagonally
     * @return True if found and false if not found
     */
    private boolean checkDiagonal(int start)
    {
        int digCountX = 0,digCountO=0;
        boolean revHorizontal = start!=2;
        for (int i = start; revHorizontal ? (i<3) : i>=0; i++) {
            i=Math.abs(i);
            Player player = board[(revHorizontal)?i:Math.abs(i-2)][i];

            if (player.getPlayerSymbol().equals("X"))
                digCountX++;
            else if (player.getPlayerSymbol().equals("O")) {
                digCountO++;
            }
            if (!revHorizontal){
                i-=2;
            }
            // If player won
            if (digCountO==3|| digCountX==3){
                updateScore(player);
                return true;
            }
        }
        // checks the second diagonal path
        if (revHorizontal){return checkDiagonal(2);}

        return false;
    }

    /**
     * Updates player score when he wins a game
     * @param player Player who won the game
     */
    private void updateScore(Player player)
    {
        scoreBoard.updateScore(player);
        int score;
        if(totalMoves[currPlayer]<=5)
            score = 300;
        else if (totalMoves[currPlayer]<=7)
            score = 200;
        else
            score = 100;
        player.setPlayerScore(score);
    }

    /**
     * Creates the Board into a string that can be printed out.
     * @return String of representation of the board.
     */
    @Override
    public String toString()
    {
        StringBuilder boardString = new StringBuilder(" ".repeat(4)+"A   B   C  \n");
        boardString.append("  +---+---+---+\n"+0+" ");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                boardString.append("| ").append(board[i][j]).append(" ");
            }
            boardString.append("|\n  +---+---+---+\n").append((i == 2) ? "" : i + 1).append(" ");
        }
        return boardString.toString();
    }

}
