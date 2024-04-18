package Updates;

import Objects.Enemy;
import Objects.Player;
import Objects.GameEntity;
import Others.GameController;

import java.util.List;

public abstract class Upgrade extends GameEntity {
    String type;

    public Upgrade(int row, int column) {
        super(row, column);

    }

    public String getType() {
        return type;
    }


    public abstract void apply(Player player, List<Enemy> enemies, GameController gameController);


}
