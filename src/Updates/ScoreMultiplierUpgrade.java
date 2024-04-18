package Updates;

import Objects.Enemy;
import Objects.Player;
import Others.GameController;
import Others.ImageLoader;

import java.util.List;

public class ScoreMultiplierUpgrade extends Upgrade {
    public ScoreMultiplierUpgrade(int row, int col) {
        super(row, col);
        setImage(ImageLoader.loadImage("./src/Images/Updates/double-score.png"));
    }

    @Override
    public void apply(Player player, List<Enemy> enemies, GameController gameController) {
        player.setHasScoreMultiplier(true);

        new Thread(() -> {
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            player.setHasScoreMultiplier(false);
        }).start();
    }
}