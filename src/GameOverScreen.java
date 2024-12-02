import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GameOverScreen extends JPanel {
    public GameOverScreen(int finalScore, JFrame frame) {
        setLayout(new BorderLayout());
        setBackground(Color.BLACK);

        // Display "Game Over" message
        JLabel gameOverLabel = new JLabel("Game Over!", JLabel.CENTER);
        gameOverLabel.setFont(new Font("Arial", Font.BOLD, 100));
        gameOverLabel.setForeground(Color.RED);
        add(gameOverLabel, BorderLayout.CENTER);

        // Display the final score
        JLabel scoreLabel = new JLabel("Your Score: " + finalScore, JLabel.CENTER);
        scoreLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        scoreLabel.setForeground(Color.WHITE);
        add(scoreLabel, BorderLayout.SOUTH);

        setFocusable(true);
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                returnToMainMenu(frame);
            }
        });
    }

    private void returnToMainMenu(JFrame frame) {
        frame.dispose();

        JFrame mainMenuFrame = new JFrame("Main Menu");

        MainMenu mainMenuPanel = new MainMenu(mainMenuFrame);

        mainMenuFrame.add(mainMenuPanel);

        mainMenuFrame.setUndecorated(true);
        mainMenuFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        mainMenuFrame.setResizable(false);
        mainMenuFrame.setVisible(true);
    }
}
