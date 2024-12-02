import javax.swing.*;
import java.awt.*;

public class HowToPlayScreen extends JFrame {
    public HowToPlayScreen(JFrame mainMenu) {
        setTitle("How To Play");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 300);
        setLayout(new BorderLayout());

        // Close the Main Menu
        if (mainMenu != null) {
            mainMenu.dispose();
        }

        // How To Play message
        String instructionsMessage = "<html><div style='text-align: center;'>"
                + "<h1>How To Play<br></h1>"
                + "<p>1. Use your mouse to aim at the fruits.<br></p>"
                + "<p>2. Click to shoot the fruits.<br></p>"
                + "<p>3. Hit the target fruit to score 5 points each time.<br></p>"
                + "<p>4. You are given 5 hearts at the start. <br>The hearts gets refilled after you clear all of the fruits.<br></p>"
                + "<p>5. If you miss a target, you'll lose a heart.<br></p>"
                + "<p>6. The game ends when you run out of hearts.<br></p>"
                + "</div></html>";

        JLabel instructionsLabel = new JLabel(instructionsMessage, JLabel.CENTER);
        instructionsLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        add(instructionsLabel, BorderLayout.CENTER);

        // Close button
        JButton closeButton = new JButton("Main Menu");
        closeButton.addActionListener(e -> {
            dispose();
            new MainMenu(mainMenu);
        });
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(closeButton);
        add(buttonPanel, BorderLayout.SOUTH);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {

        new HowToPlayScreen(null);
    }
}
