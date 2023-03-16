package TickTacToe;

import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class ScoreBoard {
    private final Map<String,Player> scoreBoard= new LinkedHashMap<>(); // Used to track players score
    private int roundCount=1;   // Keeps track of rounds playing in
    public ScoreBoard(){}

    /**
     * Updates player score
     * @param winner Player that is to be in the scoreBoard
     */
    public void updateScore(Player winner)
    {
        scoreBoard.put("Round "+roundCount++,winner);
    }

    /**
     * Creates a table of all rounds and sores
     * @return String representation of a board
     */
    @Override
    public String toString()
    {
        int extraSpace = 7;
        Set<Player> players = new HashSet<>();
        StringBuilder scoreBoardString = new StringBuilder("\n "+"_".repeat((extraSpace*extraSpace)-4)) //       ____________________________________________
                .append(String.format("\n| %1$sRounds Played%1$s"," ".repeat((extraSpace*2)+1)))        //      |                Rounds Played               |
                .append(String.format("|\n|%s|\n", "_".repeat((int) Math.pow(extraSpace,2)-5)))         //      |____________________________________________|
                .append(String.format("| Rounds%1$s| Scores%1$s| Points%1$s|\n", " ".repeat(extraSpace)));    //      | Rounds       | Scores       | Points       |

        for (Map.Entry<String,Player> round: scoreBoard.entrySet()){
            scoreBoardString.append(String.format("|%1$s|%1$s|%1$s|\n", "-".repeat(extraSpace+7)))      // |--------------|--------------|--------------|
                    .append("| "+round.getKey()+" ".repeat(extraSpace-1))                               // | Round 1
                    .append("| "+round.getValue().getEmoticon()+ " ".repeat(10))                        // | O_O
                    .append("| "+round.getValue().getPlayerScoreCurr()+ " ".repeat(10)+"|\n");          // | 200

            // My adds player to list of all players
            players.add(round.getValue());
        }
        scoreBoardString.append(" -".repeat((int) (extraSpace*4)-6))                                //  - - - - - - - - - - - - - - - - - - - - - -
                .append(String.format("\n|  %1$sTotal Points%1$s  |"," ".repeat(extraSpace*2)));    //  |                Total Points                |

        for (Player player: players) {
            scoreBoardString.append(String.format("\n|%1$s|%1$s-%1$s|", "-".repeat(extraSpace + 7)))  // |--------------|-----------------------------|
                    .append(String.format("\n| %s%s|%d", player.getEmoticon(), " ".repeat(10), player.getPlayerScoreTotal())); // | O_O          |200
        }

        return scoreBoardString.toString();
    }

}
