import Others.ScoreManager;
import Views.MainMenu;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        ScoreManager.readScores();
        SwingUtilities.invokeLater(Main::createAndShowGUI);
    }

    private static void createAndShowGUI() {
        new MainMenu();
    }
}
