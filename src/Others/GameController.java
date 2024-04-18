package Others;

import Objects.Enemy;
import Objects.GameEntity;
import Objects.Player;
import Objects.Wall;
import Updates.*;
import Views.EndGameView;
import Views.GameView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Stack;

public class GameController implements Runnable {
    private static GameBoardModel model;
    private GameView view;

    private Player player;
    private List<Enemy> enemies;
    private static List<Upgrade> upgrades;

    static List<GameEntity> entities;

    public static int rows;
    public static int columns;
    static boolean endGame;

    static int timer;
    public GameController(int rows, int columns, GameView view) {
        this.view = view;
        this.rows = rows;
        this.columns = columns;
        this.endGame = false;
        this.timer = 0;

        player = new Player(1, 1);
        enemies = new ArrayList<>();
        upgrades = new ArrayList<>();
        entities = new ArrayList<>();

        generateWalls();

        entities.add(player);
        entities.addAll(upgrades);

        UpgradesThread upgradesThread = new UpgradesThread();
        Thread ut = new Thread(upgradesThread);
        ut.start();

        TimerThread timerThread = new TimerThread();
        Thread tt = new Thread(timerThread);
        tt.start();
    }

    private void generateWalls() {
       int[][] lab = new int[rows][columns];


        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                lab[i][j] = 1;
            }
        }


        Random rand = new Random();
        Stack<Point> stack = new Stack<>();
        Point current = new Point(1, 1);
        stack.push(current);

        while (!stack.isEmpty()) {
            int x = current.x;
            int y = current.y;


            int[] directions = {0, 1, 2, 3};
            for (int i = 0; i < directions.length; i++) {
                int randomIndex = rand.nextInt(directions.length - i) + i;
                int temp = directions[i];
                directions[i] = directions[randomIndex];
                directions[randomIndex] = temp;
            }

            boolean hasUnvisitedNeighbor = false;
            for (int direction : directions) {
                switch (direction) {
                    case 0:
                        if (x >= 2 && lab[x - 2][y] == 1) {
                            lab[x - 1][y] = 0;
                            lab[x - 2][y] = 0;
                            stack.push(new Point(x - 2, y));
                            current = new Point(x - 2, y);
                            hasUnvisitedNeighbor = true;
                        }
                        break;
                    case 1:
                        if (y <= columns - 3 && lab[x][y + 2] == 1) {
                            lab[x][y + 1] = 0;
                            lab[x][y + 2] = 0;
                            stack.push(new Point(x, y + 2));
                            current = new Point(x, y + 2);
                            hasUnvisitedNeighbor = true;
                        }
                        break;
                    case 2:
                        if (x <= rows - 3 && lab[x + 2][y] == 1) {
                            lab[x + 1][y] = 0;
                            lab[x + 2][y] = 0;
                            stack.push(new Point(x + 2, y));
                            current = new Point(x + 2, y);
                            hasUnvisitedNeighbor = true;
                        }
                        break;
                    case 3: // Left
                        if (y >= 2 && lab[x][y - 2] == 1) {
                            lab[x][y - 1] = 0;
                            lab[x][y - 2] = 0;
                            stack.push(new Point(x, y - 2));
                            current = new Point(x, y - 2);
                            hasUnvisitedNeighbor = true;
                        }
                        break;
                }

                if (hasUnvisitedNeighbor) {
                    break;
                }
            }

            if (!hasUnvisitedNeighbor) {
                stack.pop();
                if (!stack.isEmpty()) {
                    current = stack.peek();
                }
            }
        }

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (lab[i][j] == 1)
                    entities.add(new Wall(i, j));
            }

        }
    }

    public void spawnEnemies(){
        Random rand = new Random();
        int numEnemies = 4;

        for (int i = 0; i < numEnemies; i++) {
            int row;
            int col;

            do {

                row = rand.nextInt(rows);
                col = rand.nextInt(columns);
            } while (model.isWallAt(row, col) || model.getEntityAt(row, col) != null);

            Enemy enemy = new Enemy(row, col);
            enemies.add(enemy);
        }
        entities.addAll(enemies);

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (model.getEntityAt(i, j) == null) {
                    GameEntity point = new GameEntity(i, j);
                    point.setHasPoint(true);
                    entities.add(point);
                }
            }
        }
    }

    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                player.setPlayerDirection(PlayerDirections.UP);
                player.setDirection(0, -1);
                break;
            case KeyEvent.VK_DOWN:
                player.setPlayerDirection(PlayerDirections.DOWN);
                player.setDirection(0, 1);
                break;
            case KeyEvent.VK_LEFT:
                player.setPlayerDirection(PlayerDirections.LEFT);
                player.setDirection(-1, 0);
                break;
            case KeyEvent.VK_RIGHT:
                player.setPlayerDirection(PlayerDirections.RIGHT);
                player.setDirection(1, 0);
                break;

            case KeyEvent.VK_Q:
                endGame();
                break;
        }
    }

    @Override
    public void run() {
        while (!endGame) {

            if (this.player.getSpeed())
                moveEntity(player, player.getDx(), player.getDy());
            moveEntity(player, player.getDx(), player.getDy());

            for (Enemy enemy : enemies) {
                if (!enemy.isFrozen()) {
                    moveEntity(enemy, enemy.getDx(), enemy.getDy());
                }
            }

            checkIfAlive();

            updateView();

            try {

                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void checkIfAlive() {
        if (player.getLives() <= 0)
            endGame();
    }

    static class UpgradesThread implements Runnable{
        @Override
        public void run() {
            while (!endGame) {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException ignored){}

                Random rand = new Random();
                if (rand.nextInt(100) < 25) {
                    
                    int row = rand.nextInt(model.getRowCount());
                    int col = rand.nextInt(model.getColumnCount());
                    while (model.isWallAt(row, col)){
                        row = rand.nextInt(model.getRowCount());
                        col = rand.nextInt(model.getColumnCount());
                    }
                    Upgrade upgrade;
                    upgrade = switch (rand.nextInt(5)) {
                        case 0 -> new SpeedUpgrade(row, col);
                        case 1 -> new FreezeUpgrade(row, col);
                        case 2 -> new ExtraLifeUpgrade(row, col);
                        case 3 -> new ScoreMultiplierUpgrade(row, col);
                        case 4 -> new TeleportUpgrade(row, col);
                        default -> null;
                    };

                    upgrades.add(upgrade);
                    entities.add(upgrade);
                    model.addEntity(upgrade);
                }
            }
        }
    }

    static class TimerThread implements Runnable{
        @Override
        public void run() {
            while(!endGame){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ignored){}
                timer++;
            }
        }
    }

    private void moveEntity(GameEntity entity, int dc, int dr) {
        int newRow = Math.max(0, Math.min(model.getRowCount() - 1, entity.getRow() + dr));
        int newCol = Math.max(0, Math.min(model.getColumnCount() - 1, entity.getColumn() + dc));

        if (entity instanceof Enemy){
            changeEnemyDirection(entity);
            return;
        }
        if (model.isWallAt(newRow, newCol)){
            return;
        }

        if (entity instanceof Player) {
            GameEntity nextEntity = model.getEntityAt(newRow, newCol);
            if (nextEntity != null && nextEntity.hasPoint()) {

                nextEntity.setHasPoint(false);
                entities.remove(nextEntity);
                model.removeEntity(nextEntity);
                model.fireTableDataChanged();

                ((Player) entity).increaseScore();
            }


            for (Upgrade upgrade : upgrades) {
                if (player.getRow() == upgrade.getRow() && player.getColumn() == upgrade.getColumn()) {
                    upgrade.apply(player, enemies, this);
                    upgrades.remove(upgrade);
                    entities.remove(upgrade);
                    model.removeEntity(upgrade);
                    break;
                }
            }
            for (Enemy enemy : enemies) {
                if (player.getRow() == enemy.getRow() && player.getColumn() == enemy.getColumn()) {
                    player.loseLife();
                    break;
                }
            }
        }
        entity.setRow(newRow);
        entity.setColumn(newCol);
    }

    private void changeEnemyDirection(GameEntity entity) {
        int[] dx = {0, 0, -1, 1};
        int[] dy = {-1, 1, 0, 0};

        ArrayList<Integer> validDirections = new ArrayList<>();


        for (int i = 0; i < dx.length; i++) {
            int newRow = entity.getRow() + dy[i];
            int newCol = entity.getColumn() + dx[i];


            if (!model.isWallAt(newRow, newCol)) {
                validDirections.add(i);
            }
        }


        if (!validDirections.isEmpty()) {
            Random rand = new Random();
            int newDirectionIndex = validDirections.get(rand.nextInt(validDirections.size()));

            entity.setDirection(dx[newDirectionIndex], dy[newDirectionIndex]);

            int newRow = Math.max(0, Math.min(model.getRowCount() - 1, entity.getRow() + entity.getDy()));
            int newCol = Math.max(0, Math.min(model.getColumnCount() - 1, entity.getColumn() + entity.getDx()));
            entity.setRow(newRow);
            entity.setColumn(newCol);
        }
    }

    public void endGame() {
        endGame = true;
        this.view.setVisible(false);
        new EndGameView(player.getScore());
    }

    public List<GameEntity> getEntities() {
        return entities;
    }
    private void updateView() {
        model.fireTableDataChanged();
        view.updateTime(timer);
        view.updateLives(player.getLives());
        view.updateScore(player.getScore());
        view.setGameBoard(new JTable(model));
        view.getGameBoard().setDefaultRenderer(Object.class, new GameBoardCellRenderer(model));
        view.repaint();
    }

    public void setModel(GameBoardModel model) {
        this.model = model;
    }
}
