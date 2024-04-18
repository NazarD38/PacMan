package Updates;

import Objects.Enemy;
import Objects.Player;
import Others.GameController;
import Others.ImageLoader;

import java.util.List;

public class ExtraLifeUpgrade extends Upgrade {
    public ExtraLifeUpgrade(int row, int col) {
        super(row, col);
        setImage(ImageLoader.loadImage("./src/Images/Updates/extra-life.png"));
    }

    @Override
    public void apply(Player player, List<Enemy> enemies, GameController gameController) {
        player.gainLife();
    }
}