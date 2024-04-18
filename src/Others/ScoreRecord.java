package Others;

import java.io.Serializable;

public class ScoreRecord implements Serializable {
    private final String playerName;
    private final int score;

    public ScoreRecord(String playerName, int score) {
        this.playerName = playerName;
        this.score = score;
    }

    public String getPlayerName() {
        return playerName;
    }

    public int getScore() {
        return score;
    }

    @Override
    public String toString() {
        return playerName + " --> " + score;
    }
}
