package Views;


import javax.swing.*;
import java.awt.*;


public class MainMenu extends JFrame {

    private JButton newGameButton;
    private JButton highScoresButton;
    private JButton exitButton;


    public MainMenu() {

        setTitle("Menu");
        ImageIcon icon = new ImageIcon("src/Icons/P.png");
        setIconImage(icon.getImage());
        setSize(600, 300);



        newGameButton = new JButton("NEW GAME");
        newGameButton.setBackground(Color.yellow);

        highScoresButton = new JButton("HIGH SCORES");
        highScoresButton.setBackground(Color.red);

        exitButton = new JButton("EXIT");
        exitButton.setBackground(Color.gray);

        JPanel buttonPanel = new JPanel(new GridLayout(3, 1));
        buttonPanel.add(newGameButton);
        buttonPanel.add(highScoresButton);
        buttonPanel.add(exitButton);

        add(buttonPanel);

        exitButton.addActionListener(e -> System.exit(0));

        newGameButton.addActionListener(e -> {new SizeView(); setVisible(false);});

        highScoresButton.addActionListener(e -> {new HighScoresView(); setVisible(false);});

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);

    }
}