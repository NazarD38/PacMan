package Updates;

import Objects.Enemy;
import Objects.Player;
import Others.GameController;
import Others.ImageLoader;

import java.util.List;

public class FreezeUpgrade extends Upgrade {
    public FreezeUpgrade(int row, int col) {
        super(row, col);
        setImage(ImageLoader.loadImage("./src/Images/Updates/froze.png"));
    }

    @Override
    public void apply(Player player, List<Enemy> enemies, GameController gameController) {
        player.freezeEnemies(enemies);
    }
}