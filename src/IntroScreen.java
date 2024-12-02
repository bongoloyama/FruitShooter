import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class IntroScreen extends JFrame {

    public IntroScreen(JFrame frame) {
        getContentPane().setBackground(Color.BLACK);
        setTitle("Welcome");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(Color.BLACK);

        JLabel welcomeLabel = new JLabel("WELCOME TO THE WORLD OF");
        welcomeLabel.setFont(new Font("Serif", Font.BOLD, 80));
        welcomeLabel.setForeground(Color.decode("#663399"));
        welcomeLabel.setBounds(150, 100, 1800, 100);
        panel.add(welcomeLabel);

        JLabel fruitHuntingLabel = new JLabel("FRUIT HUNTING");
        fruitHuntingLabel.setFont(new Font("Serif", Font.BOLD, 50));
        fruitHuntingLabel.setForeground(Color.decode("#8B0000"));
        fruitHuntingLabel.setBounds(550, 250, 1800, 100);
        panel.add(fruitHuntingLabel);

        JLabel goodLuckLabel = new JLabel("Wishing you good luck");
        goodLuckLabel.setFont(new Font("Serif", Font.BOLD, 30));
        goodLuckLabel.setForeground(Color.YELLOW);
        goodLuckLabel.setBounds(600, 400, 1800, 100);
        panel.add(goodLuckLabel);

        JLabel pressKeyLabel = new JLabel("Press any key to continue.....");
        pressKeyLabel.setFont(new Font("Serif", Font.BOLD | Font.ITALIC, 25));
        pressKeyLabel.setForeground(Color.WHITE);
        pressKeyLabel.setBounds(600, 700, 1800, 100);
        panel.add(pressKeyLabel);

        add(panel);

        // Blinking
        new Timer(500, e -> {
            if (pressKeyLabel.getForeground().equals(Color.WHITE)) {
                pressKeyLabel.setForeground(Color.BLACK);
            } else {
                pressKeyLabel.setForeground(Color.WHITE);
            }
        }).start();

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                startMainMenu(frame);
            }
        });

        setVisible(true);
    }

    private void startMainMenu(JFrame frame) {
        dispose();
        new MainMenu(frame);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        new IntroScreen(frame);
    }
}
