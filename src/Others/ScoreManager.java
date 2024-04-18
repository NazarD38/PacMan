package Others;

import java.io.*;
import java.util.ArrayList;
public class ScoreManager {
    private static final String SCORES_FILE = "./src/scores.dat";

    public static ArrayList<ScoreRecord> scoreRecords = new ArrayList<>();
    public static void readScores() {
        try (BufferedReader br = new BufferedReader(new FileReader(SCORES_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(" ");
                scoreRecords.add(new ScoreRecord(parts[0], Integer.parseInt(parts[1])));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void appendRecord(String name, int score) {
        scoreRecords.add(new ScoreRecord(name, score));
        try (FileWriter fw = new FileWriter(SCORES_FILE, true)) {
            fw.write("\n" + name + " " + score);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
