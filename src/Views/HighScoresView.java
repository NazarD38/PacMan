package Views;

import Others.GameController;
import Others.ScoreManager;
import Others.ScoreRecord;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class HighScoresView extends JFrame {
    private JList<ScoreRecord> highScoresList;

    public HighScoresView() {

        setSize(500, 500);
        setTitle("HighScore");
        ImageIcon icon = new ImageIcon("src/Icons/P.png");
        setIconImage(icon.getImage());

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        ScoreManager.scoreRecords.sort(Comparator.comparingInt(ScoreRecord::getScore).reversed());
        ScoreRecord[] records = new ScoreRecord[ScoreManager.scoreRecords.size()];
        ScoreManager.scoreRecords.toArray(records);

        highScoresList = new JList<>(records);


        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> {
            setVisible(false);
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

        JPanel panel = new JPanel();
        panel.add(new JScrollPane(highScoresList));
        panel.add(backButton);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        add(panel);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setFocusable(true);
        requestFocus();
        setVisible(true);
    }
    private void returnToMainMenu() {
        dispose();
        MainMenu mainMenu = new MainMenu();
        mainMenu.setVisible(true);
    }
}
