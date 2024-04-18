package Objects;

import Others.GameController;
import Others.ImageLoader;
import Others.PlayerDirections;

import java.util.List;
import java.util.Random;

public class Player extends GameEntity {

    boolean speed;
    private int lives;

    private int score;

    private boolean hasScoreMultiplier;

    private PlayerDirections playerDirection;

    String name = "";
    boolean Running=true;
    public Player(int row, int column) {
        super(row, column);
        this.playerDirection = PlayerDirections.RIGHT;
        this.speed = false;
        this.lives = 3;
        this.hasScoreMultiplier = false;
        this.setImage(ImageLoader.loadImage("./src/Images/Pacman/RIGHT/pacman-closed.png"));
        this.imagePath = "./src/Images/Pacman/RIGHT/pacman-closed.png";

        new Thread(() -> {
            while (Running) {
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                StringBuilder pathBuilder = new StringBuilder("./src/Images/Pacman/").append(playerDirection).append("/");

                if (this.imageIs("./src/Images/Pacman/pacman-closed.png")) {
                    pathBuilder.append("pacman-2.png");
                    this.setImage(ImageLoader.loadImage(pathBuilder.toString()));
                    this.imagePath = "./src/Images/Pacman/pacman-2.png";
                }
                else if(this.imageIs("./src/Images/Pacman/pacman-2.png")){
                    pathBuilder.append("pacman-1.png");
                    this.setImage(ImageLoader.loadImage(pathBuilder.toString()));
                    this.imagePath = "./src/Images/Pacman/pacman-1.png";
                }
                else if(this.imageIs("./src/Images/Pacman/pacman-1.png")){
                    pathBuilder.append("pacman-3.png");
                    this.setImage(ImageLoader.loadImage(pathBuilder.toString()));
                    this.imagePath = "./src/Images/Pacman/pacman-3.png";
                }
                else {
                    pathBuilder.append("pacman-closed.png");
                    this.setImage(ImageLoader.loadImage(pathBuilder.toString()));
                    this.imagePath = "./src/Images/Pacman/pacman-closed.png";
                }
            }
        }).start();
    }

    public void increaseSpeed() {
        speed = true;
    }

    public void decreaseSpeed() {
        speed = false;
    }

    public void freezeEnemies(List<Enemy> enemies) {
        for (Enemy enemy : enemies) {
            enemy.freeze();
        }


        new Thread(() -> {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            for (Enemy enemy : enemies) {
                enemy.unfreeze();
            }
        }).start();
    }

    public void teleport(int rows, int columns, GameController gameController) {
        Random rand = new Random();
        int newRow = 0, newCol = 0;
        boolean found = false;

        while (!found){
            newRow= rand.nextInt(rows);
            newCol = rand.nextInt(columns);
            for (GameEntity entity : gameController.getEntities()) {
                if (!(entity instanceof Wall && entity.getRow() == newRow && entity.getColumn() == newCol)){
                    found = true;
                    break;
                }
            }
        }

        setRow(newRow);
        setColumn(newCol);
    }
    public int getScore() {
        return score;
    }

    public void increaseScore() {
        if (hasScoreMultiplier){
            score++;
        }
        score ++;
    }

    public boolean getSpeed() {
        return speed;
    }

    public void gainLife() {
        lives++;
    }

    public int getLives() {
        return lives;
    }

    public void loseLife() {
        lives--;
    }

    public String getName() {
        return this.name;
    }
    public void setHasScoreMultiplier(boolean hasScoreMultiplier) {
        this.hasScoreMultiplier = hasScoreMultiplier;
    }

    public void setPlayerDirection(PlayerDirections playerDirection) {
        this.playerDirection = playerDirection;
    }
}
