import javax.swing.*;
import java.awt.*;

public class AboutScreen extends JFrame {
    public AboutScreen(JFrame mainMenu) {
        setTitle("About");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 300);
        setLayout(new BorderLayout());

        if (mainMenu != null) {
            mainMenu.dispose();
        }

        // About message
        String aboutMessage = "<html><div style='text-align: center;'>"
                + "<h1>About Fruit Shooter<br></h1>"
                + "<p>This game was made by-<br></p>"
                + "<p>Sarkar Fahim Foysal Niloy<br>CE21028<br></p>"
                + "<p>Tanvir Ahmed<br>CE21032<br></p>"
                + "<p>Under the supervision of-<br>Md. Mosaddik Hasan Sir<br>Associate Professor<br>Department of CSE<br>Mawlana Bhashani Science and Technology University</p>"
                + "</div></html>";

        JLabel aboutLabel = new JLabel(aboutMessage, JLabel.CENTER);
        aboutLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        add(aboutLabel, BorderLayout.CENTER);

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
    }
}
