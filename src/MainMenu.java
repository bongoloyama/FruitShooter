import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainMenu extends JFrame {
    private HighScoreManager highScoreManager;

    public MainMenu(JFrame mainMenu) {
        this();
        if (mainMenu != null) {
            mainMenu.dispose();
        }
    }

    public MainMenu() {
        this.highScoreManager = new HighScoreManager();

        setTitle("Fruit Shooter");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setUndecorated(true);
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice gd = ge.getDefaultScreenDevice();
        if (gd.isFullScreenSupported()) {
            gd.setFullScreenWindow(this);
        } else {
            setExtendedState(JFrame.MAXIMIZED_BOTH);
        }

        // Layout and buttons
        setLayout(new GridLayout(4, 1, 10, 10));

        JButton newGameButton = createButton("New Game", new Color(0, 123, 255), Color.WHITE);
        JButton howToPlayButton = createButton("How To Play", new Color(40, 167, 69), Color.WHITE);
        JButton aboutButton = createButton("About", new Color(255, 193, 7), Color.BLACK);
        JButton exitButton = createButton("Exit", new Color(220, 53, 69), Color.WHITE);

        // Mouse hover effect (color change)
        newGameButton.addMouseListener(new ButtonHoverListener(newGameButton));
        howToPlayButton.addMouseListener(new ButtonHoverListener(howToPlayButton));
        aboutButton.addMouseListener(new ButtonHoverListener(aboutButton));
        exitButton.addMouseListener(new ButtonHoverListener(exitButton));

        // New Game
        newGameButton.addActionListener(e -> {
            GamePanel gamePanel = new GamePanel(this);

            setContentPane(gamePanel);
            revalidate();
            repaint();

            setVisible(true);
        });

        // How To Play
        howToPlayButton.addActionListener(e -> new HowToPlayScreen(this));

        // About
        aboutButton.addActionListener(e -> new AboutScreen(this));

        // Exit
        exitButton.addActionListener(e -> System.exit(0));

        add(newGameButton);
        add(howToPlayButton);
        add(aboutButton);
        add(exitButton);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private JButton createButton(String text, Color backgroundColor, Color textColor) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 50));
        button.setBackground(backgroundColor);
        button.setForeground(textColor);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setOpaque(true);
        return button;
    }

    private class ButtonHoverListener extends MouseAdapter {
        private final JButton button;

        public ButtonHoverListener(JButton button) {
            this.button = button;
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            button.setForeground(Color.YELLOW);
            button.setBackground(button.getBackground().darker());
        }

        @Override
        public void mouseExited(MouseEvent e) {
            button.setForeground(Color.WHITE);
            button.setBackground(button.getBackground().brighter());
        }
    }

    public static void main(String[] args) {
        MainMenu mainMenu = new MainMenu();

        new IntroScreen(mainMenu);
    }
}
