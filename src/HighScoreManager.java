import java.io.*;
import java.util.*;

public class HighScoreManager {
    private static final String FILE_NAME = "high_scores.txt"; // File to store the high scores
    private static final int MAX_SCORES = 10; // Maximum number of high scores to keep
    private List<Score> highScores;

    public HighScoreManager() {
        highScores = new ArrayList<>();
        loadHighScores();
    }

    private void loadHighScores() {
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            // Create blank scores
            createNewHighScoreFile();
        } else {
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = br.readLine()) != null) {
                    String[] parts = line.split(",");
                    if (parts.length == 2) {
                        String name = parts[0];
                        int score = Integer.parseInt(parts[1]);
                        highScores.add(new Score(name, score));
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void createNewHighScoreFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (int i = 0; i < MAX_SCORES; i++) {
                writer.write("Blank,0\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveHighScores() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Score score : highScores) {
                writer.write(score.getName() + "," + score.getScore() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addHighScore(String name, int score) {
        highScores.add(new Score(name, score));
        Collections.sort(highScores);
        if (highScores.size() > MAX_SCORES) {
            highScores.remove(MAX_SCORES);
        }
        saveHighScores();
    }

    public List<Score> getHighScores() {
        return highScores;
    }
}
