package TickTacToe;

import CusMeth.RalphsList;

import java.util.ArrayList;
import java.util.Iterator;

public class Player{
    private final String playerSymbol;  // X/O
    private String emoticon;    // X_X / O_O / A_A
    private final boolean usingAi;  // if you want to use Ai
    private RalphsList<Integer> playerScore = new RalphsList<>(); //Keeps track of all players scores for each round
    private Iterator<Integer> itr = playerScore.iterator();


    /**
     * Creates a player object of person or Ai
     * @param playerInput Player Input
     * @param isAi Determines if player is an Ai or not
     */
    public Player(String playerInput, boolean isAi)
    {
        this.playerSymbol = playerInput.toUpperCase();
        this.usingAi = isAi;
        emoticon =  (isAi)
                ? "A_A" // If player is Ai
                : playerSymbol.equals("X") // If player is human
                    ? "X_X"
                    : "O_O";
    }

    /**
     * Returns player Emoticon
     * @return  Returns player profile
     */
    public String getEmoticon() {
        return emoticon;
    }

    /**
     * Returns players current score
     * @return Current score
     */
    public int getPlayerScoreCurr() {
        if (playerScore.size()!=0){
            return itr.next();
        }
        return 0;
    }

    /**
     * Returns players current score
     * @return Current score
     */
    public int getPlayerScoreTotal() {
        itr = playerScore.iterator();
        return (playerScore.stream().reduce(0, Integer::sum));
    }

    /**
     * Returns player Symbol - X/O
     * @return Player symbol
     */
    public String getPlayerSymbol() {
        return playerSymbol;
    }

    /**
     * Updates player score during a game
     * @param playerScore what its being increased by
     */
    public void setPlayerScore(int playerScore) {
        this.playerScore.add(playerScore);
    }

    /**
     * Returns player Symbol - X/O
     * @return Player symbol
     */
    @Override
    public String toString() {
        return playerSymbol;
    }
}
