package Views;

import Others.GameBoardCellRenderer;
import Others.GameBoardModel;
import Others.GameController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameView extends JFrame {
    private JTable gameBoard;
    private GameController controller;

    private GameBoardModel model;

    private JLabel timeLabel;
    private JLabel livesLabel;
    private JLabel scoreLabel;
    public GameView(int rows, int columns) {

        JPanel counterPanel = new JPanel();
        counterPanel.setLayout(new FlowLayout());


        timeLabel = new JLabel("Time: 0");
        timeLabel.setFont(new Font("Arial", Font.BOLD, 16));
        counterPanel.add(timeLabel, BorderLayout.WEST);


        livesLabel = new JLabel("Lives: 3");
        livesLabel.setFont(new Font("Arial", Font.BOLD, 16));
        counterPanel.add(livesLabel, BorderLayout.CENTER);


        scoreLabel = new JLabel("Score: 0");
        scoreLabel.setFont(new Font("Arial", Font.BOLD, 16));
        counterPanel.add(scoreLabel, BorderLayout.EAST);


        add(counterPanel, BorderLayout.NORTH);


        GameController controller = new GameController(rows, columns, this);
        this.setController(controller);
        this.model = new GameBoardModel(rows, columns, controller.getEntities());
        controller.setModel(model);
        controller.spawnEnemies();

        gameBoard = new JTable(model);
        gameBoard.setDefaultRenderer(Object.class, new GameBoardCellRenderer(model));  // Add this line
        gameBoard.setRowHeight(15);
        for (int i = 0; i < columns; i++) {
            gameBoard.getColumnModel().getColumn(i).setPreferredWidth(15);
        }
        int tableWidth = columns * 15;
        int tableHeight = rows * 15;
        setSize(tableWidth, tableHeight);
        gameBoard.setIntercellSpacing(new Dimension(0, 0));
        gameBoard.setGridColor(Color.BLACK);
        add(gameBoard);
        pack();

        new Thread(controller).start();

        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {

                if (e.isControlDown() && e.isShiftDown() && e.getKeyCode() == KeyEvent.VK_Q) {

                    returnToMainMenu();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                controller.keyPressed(e);
            }
        });

        setFocusable(true);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
    }
    private void returnToMainMenu() {
        dispose();
        MainMenu mainMenu = new MainMenu();
        mainMenu.setVisible(true);
    }


    public void setController(GameController controller) {
        this.controller = controller;
    }

    public GameBoardModel getModel() {
        return model;
    }

    public void setGameBoard(JTable gameBoard) {
        this.gameBoard = gameBoard;
    }

    public void setModel(GameBoardModel model) {
        this.model = model;
    }

    public JTable getGameBoard() {
        return gameBoard;
    }

    public GameController getController() {
        return controller;
    }

    public void updateTime(int time) {
        timeLabel.setText("Time: " + time);
    }

    public void updateLives(int lives) {
        livesLabel.setText("Lives: " + lives);
    }

    public void updateScore(int score) {
        scoreLabel.setText("Score: " + score);
    }
}
