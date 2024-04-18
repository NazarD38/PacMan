package Updates;

import Objects.Enemy;
import Objects.Player;
import Others.GameController;
import Others.ImageLoader;

import java.util.List;

public class SpeedUpgrade extends Upgrade {
    public SpeedUpgrade(int row, int col) {
        super(row, col);
        this.type = "Speed";
        setImage(ImageLoader.loadImage("./src/Images/Updates/speed.png"));
    }

    @Override
    public void apply(Player player, List<Enemy> enemies, GameController gameController) {
        player.increaseSpeed();
        // Decrease speed back to normal after 5 seconds
        new Thread(() -> {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            player.decreaseSpeed();
        }).start();
    }
}