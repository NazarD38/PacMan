package Views;

import Others.GameController;
import Others.ScoreManager;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class EndGameView extends JFrame {
    private GameController controller;
    public EndGameView(int score) {
        JLabel scoreLabel = new JLabel("Your Score: " + score);
        JLabel enterYourName = new JLabel("Enter Your Name: ");
        JTextField name = new JTextField(20);
        JButton submitButton = new JButton("Submit");

        submitButton.addActionListener(e -> {
            setVisible(false);
            ScoreManager.appendRecord(name.getText(), score);
            new MainMenu();
        });

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

        JPanel mainPanel = new JPanel();

        JPanel scorePanel = new JPanel();
        scorePanel.add(scoreLabel);

        JPanel namePanel = new JPanel();
        namePanel.add(enterYourName);
        namePanel.add(name);

        mainPanel.add(scorePanel);
        mainPanel.add(namePanel);
        mainPanel.add(submitButton);
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        add(mainPanel);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setFocusable(true);
        requestFocus();
        setVisible(true);
        pack();
    }

    private void returnToMainMenu() {
        dispose();
        MainMenu mainMenu = new MainMenu();
        mainMenu.setVisible(true);
    }
}
