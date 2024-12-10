package Assignment3C2110;

import java.util.HashMap;
import java.util.Map;
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
    public String playerWithMostPoints() {
        return bestPlayersInGivenCategory(
                playerRecord -> playerRecord.getGoalsScored() + playerRecord.getAssists(),
                playerRecord -> String.format(
                        "Name: %s, Team: %s\n",
                        playerRecord.getName(), playerRecord.getTeamName()
                )
        );
    }

    /**
     * Retrieves the most aggressive player
     */
    public String mostAggressivePlayer() {
        return bestPlayersInGivenCategory(
                PlayerRecord::getPenaltiesInMinutes,
                playerRecord -> String.format(
                        "Name: %s, Team: %s, Position %s\n",
                        playerRecord.getName(), playerRecord.getTeamName(), playerRecord.getPosition()
                )
        );
    }

    /**
     * Gets the MVP for the game
     */
    public String getMVP() {
        return bestPlayersInGivenCategory(
                PlayerRecord::getGameWins,
                playerRecord -> String.format(
                        "Name: %s, Team: %s\n",
                        playerRecord.getName(), playerRecord.getTeamName()
                )
        );
    }

    /**
     * Gets the most promising player in the game
     */
    public String mostPromisingPlayer() {
        return bestPlayersInGivenCategory(
                PlayerRecord::getShotsOnGoal,
                playerRecord -> String.format(
                        "Name: %s, Team: %s\n",
                        playerRecord.getName(), playerRecord.getTeamName()
                )
        );
    }

    /**
     * Gets players with the highest penalty minutes
     */
    public String mostPenaltyMinutes() {
        return bestTeamsInGivenCategory(
                PlayerRecord::getPenaltiesInMinutes,
                teamNameAndCount -> String.format(
                        "Team: %s\n",
                        teamNameAndCount.getKey()
                )
        );
    }

    /**
     * Gets player with most game wins
     */
    public String mostGameWins() {
        return bestTeamsInGivenCategory(
                PlayerRecord::getGameWins,
                teamNameAndCount -> String.format(
                        "Team: %s\n",
                        teamNameAndCount.getKey()
                )
        );
    }

    /**
     * Finds the best players for a given category using a `category` of your choosing
     *
     * @param category The category you want to check for
     * @param output   What to output when found
     */
    private String bestPlayersInGivenCategory(Function<PlayerRecord, Integer> category,
                                              Function<PlayerRecord, String> output) {
        int max = -1;
        for (PlayerRecord playerRecord : playerRecordList) {
            max = Math.max(max, category.apply(playerRecord));
        }

        StringBuilder stringBuilder = new StringBuilder();
        for (PlayerRecord playerRecord : playerRecordList) {
            if (max == category.apply(playerRecord))
                stringBuilder.append(output.apply(playerRecord));
        }
        return stringBuilder.toString();
    }

    /**
     * Calculates the best team for given categories using the `category` and then
     * outputs to the console given from the `output` variable
     *
     * @param category The requirements to match for the team
     * @param output   What to output to the screen for those who match the requirement
     */
    private String bestTeamsInGivenCategory(Function<PlayerRecord, Integer> category,
                                            Function<Map.Entry<String, Integer>, String> output) {
        // Prepare the zstat table for each team based on the category given
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
        StringBuilder stringBuilder = new StringBuilder();
        for (Map.Entry<String, Integer> teams : teamStatsTable.entrySet()) {
            if (teams.getValue() == maxStats)
                stringBuilder.append(output.apply(teams));
        }
        return stringBuilder.toString();
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
