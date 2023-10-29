package Assignment3C2110;

/**
 * Represents Positions of the players
 */
enum Position {
    CENTER("Center"),
    GOALIE("Goalie"),
    LEFT_DEFENCE("Left Defence"),
    LEFT_WING("Left Wing"),
    RIGHT_DEFENCE("Right Defence"),
    RIGHT_WING("Right Wing"),
    DEFENCE("Defence");

    private final String displayName;

    Position(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }
}


public class PlayerRecord {
    private final String name;
    private final Position position;
    private final String teamName;
    private final int gamesPlayed;
    private final int goalsScored;
    private final int assists;
    private final int penaltiesInMinutes;
    private final int shotsOnGoal;
    private final int gameWins;

    public PlayerRecord(String name, String pos, String teamName, int gamesPlayed, int goalsScored, int assists, int penaltiesInMinutes, int shotsOnGoal, int gameWins) {
        this.name = name;
        this.position = switch (pos) {
            case "C" -> Position.CENTER;
            case "LW" -> Position.LEFT_WING;
            case "RW" -> Position.RIGHT_WING;
            case "LD" -> Position.LEFT_DEFENCE;
            case "RD" -> Position.RIGHT_DEFENCE;
            case "G" -> Position.GOALIE;
            case "D" -> Position.DEFENCE;
            default -> throw new IllegalArgumentException(String.format("The Position %s given is not valid.", pos));
        };
        this.teamName = teamName;
        this.gamesPlayed = gamesPlayed;
        this.goalsScored = goalsScored;
        this.assists = assists;
        this.penaltiesInMinutes = penaltiesInMinutes;
        this.shotsOnGoal = shotsOnGoal;
        this.gameWins = gameWins;
    }
    public String getName() {
        return name;
    }

    public Position getPosition() {
        return position;
    }

    public String getTeamName() {
        return teamName;
    }

    public int getGamesPlayed() {
        return gamesPlayed;
    }

    public int getGoalsScored() {
        return goalsScored;
    }

    public int getAssists() {
        return assists;
    }

    public int getPenaltiesInMinutes() {
        return penaltiesInMinutes;
    }

    public int getShotsOnGoal() {
        return shotsOnGoal;
    }

    public int getGameWins() {
        return gameWins;
    }
}
