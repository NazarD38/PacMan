package Updates;

import Objects.Enemy;
import Objects.Player;
import Others.GameController;
import Others.ImageLoader;

import java.util.List;

public class TeleportUpgrade extends Upgrade {
    public TeleportUpgrade(int row, int col) {
        super(row, col);
        setImage(ImageLoader.loadImage("./src/Images/Updates/teleport.png"));
    }



    @Override
    public void apply(Player player, List<Enemy> enemies, GameController gameController) {
        player.teleport(gameController.rows, gameController.columns, gameController);
    }
}