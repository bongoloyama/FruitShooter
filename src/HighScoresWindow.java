import javax.swing.*;
import java.awt.*;
import java.util.List;

public class HighScoresWindow extends JFrame {
    public HighScoresWindow(JFrame mainMenu, HighScoreManager highScoreManager) {
        setTitle("High Scores");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 400);
        setLayout(new BorderLayout());

        if (mainMenu != null) {
            mainMenu.dispose();
        }

        // Display high scores
        StringBuilder highScoresText = new StringBuilder("<html><h1 style='text-align: center;'>High Scores</h1>");
        List<Score> highScores = highScoreManager.getHighScores();
        for (int i = 0; i < highScores.size(); i++) {
            Score score = highScores.get(i);
            String rank = (i + 1 < 10) ? "0" + (i + 1) : String.valueOf(i + 1);
            highScoresText.append("<p>").append(rank).append(". ").append(score.getName())
                    .append(" - ").append(score.getScore()).append("</p>");
        }
        highScoresText.append("</html>");
        JLabel highScoresLabel = new JLabel(highScoresText.toString(), JLabel.CENTER);
        highScoresLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        add(highScoresLabel, BorderLayout.CENTER);

        // Main Menu button
        JButton mainMenuButton = new JButton("Main Menu");
        mainMenuButton.addActionListener(e -> {
            dispose();
            new MainMenu();
        });
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(mainMenuButton);
        add(buttonPanel, BorderLayout.SOUTH);

        setLocationRelativeTo(null);
        setVisible(true);
    }
}
