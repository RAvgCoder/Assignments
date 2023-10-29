package Assignment3C2110;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;

public class NHLStats {
    private final LinkedList<PlayerRecord> playerRecordList;

    public NHLStats() {
        this.playerRecordList = new LinkedList<>();
    }

    public void addPlayerRecord(PlayerRecord playerRecord) {
        playerRecordList.add(playerRecord);
    }

    /**
     * Retrieves player with the most points
     */
    public void playerWithMostPoints() {
        bestPlayersInGivenCategory(
                playerRecord -> playerRecord.getGoalsScored() + playerRecord.getAssists(),
                playerRecord -> System.out.printf(
                        "Name: %s, Team: %s\n",
                        playerRecord.getName(), playerRecord.getTeamName()
                )
        );
    }

    /**
     * Retrieves the most aggressive player
     */
    public void mostAggressivePlayer() {
        bestPlayersInGivenCategory(
                PlayerRecord::getPenaltiesInMinutes,
                playerRecord -> System.out.printf(
                        "Name: %s, Team: %s, Position %s\n",
                        playerRecord.getName(), playerRecord.getTeamName(), playerRecord.getPosition()
                )
        );
    }

    /**
     * Gets the MVP for the game
     */
    public void getMVP() {
        bestPlayersInGivenCategory(
                PlayerRecord::getGameWins,
                playerRecord -> System.out.printf(
                        "Name: %s, Team: %s\n",
                        playerRecord.getName(), playerRecord.getTeamName()
                )
        );
    }

    /**
     * Gets the most promising player in the game
     */
    public void mostPromisingPlayer() {
        bestPlayersInGivenCategory(
                PlayerRecord::getShotsOnGoal,
                playerRecord -> System.out.printf(
                        "Name: %s, Team: %s\n",
                        playerRecord.getName(), playerRecord.getTeamName()
                )
        );
    }

    /**
     * Gets players with the highest penalty minutes
     */
    public void mostPenaltyMinutes() {
        bestTeamsInGivenCategory(
                PlayerRecord::getPenaltiesInMinutes,
                teamNameAndCount -> System.out.printf(
                        "Team: %s\n",
                        teamNameAndCount.getKey()
                )
        );
    }

    /**
     * Gets player with most game wins
     */
    public void mostGameWins() {
        bestTeamsInGivenCategory(
                PlayerRecord::getGameWins,
                teamNameAndCount -> System.out.printf(
                        "Team: %s\n",
                        teamNameAndCount.getKey()
                )
        );
    }

    /**
     * Finds the best players for a given category using a `category` of your choosing
     * @param category The category you want to check for
     * @param output What to output when found
     */
    private void bestPlayersInGivenCategory(Function<PlayerRecord, Integer> category,
                                            Consumer<PlayerRecord> output) {
        //
        int max = -1;
        for (PlayerRecord playerRecord : playerRecordList) {
            max = Math.max(max, category.apply(playerRecord));
        }

        for (PlayerRecord playerRecord : playerRecordList) {
            if (max == category.apply(playerRecord))
                output.accept(playerRecord);
        }
    }

    /**
     * Calculates the best team for given categories using the `category` and then 
     * outputs to the console given from the `output` variable
     * @param category The requirements to match for the team
     * @param output What to output to the screen for those who match the requirement
     */
    private void bestTeamsInGivenCategory(Function<PlayerRecord, Integer> category,
                                          Consumer<Map.Entry<String, Integer>> output) {
        // Prepare the stats table for each team based on the category given
        HashMap<String, Integer> teamStatsTable = new HashMap<>();
        for (PlayerRecord playerRecord : playerRecordList) {
            String name = playerRecord.getTeamName();
            int stats = category.apply(playerRecord);
            if (teamStatsTable.containsKey(name)) {
                teamStatsTable.put(
                        name,
                        teamStatsTable.get(name) + stats
                );
            } else teamStatsTable.put(name, stats);
        }
        
        // Find the team with the highest stats
        int maxStats = -1;
        for (Integer teamPenalties : teamStatsTable.values()) {
            maxStats = Math.max(maxStats, teamPenalties);
        }
        for (Map.Entry<String, Integer> teams : teamStatsTable.entrySet()) {
            if (teams.getValue() == maxStats)
                output.accept(teams);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (PlayerRecord p : playerRecordList) {
            sb.append(String.format("Name: %s, Team: %s\n", p.getName(), p.getTeamName()));
        }
        return sb.toString();
    }
}
