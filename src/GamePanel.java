import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.List;

public class GamePanel extends JPanel implements ActionListener, MouseListener, MouseMotionListener, KeyListener {
    private JFrame frame;
    private Timer timer;
    private ArrayList<Fruit> fruits;
    private Shooter shooter;
    private ArrayList<Projectile> projectiles;
    private int screenWidth, screenHeight;
    private String targetFruit;
    private int score;
    private int hearts;

    private int yellowCount = 0;
    private int redCount = 0;
    private int blueCount = 0;
    private int greenCount = 0;


    private List<String> targetSequence = List.of(
            "Red", "Blue", "Green", "Red", "Yellow",
            "Green", "Blue", "Green", "Red", "Yellow", "Blue", "Yellow");
    private int targetSequenceIndex = 0;

    // Heart image
    private Image heartImage;

    public GamePanel(JFrame frame) {
        this.frame = frame;
        setBackground(Color.WHITE);
        addMouseListener(this);
        addMouseMotionListener(this);
        addKeyListener(this);
        setFocusable(true);

        fruits = new ArrayList<>();
        projectiles = new ArrayList<>();
        shooter = new Shooter();
        score = 0;
        hearts = 5;

        // Load fruit images
        String yellowFruitImage = "/images/yellow_fruit.png";
        String redFruitImage = "/images/red_fruit.png";
        String blueFruitImage = "/images/blue_fruit.png";
        String greenFruitImage = "/images/green_fruit.png";
        heartImage = new ImageIcon(getClass().getResource("/images/heart.png")).getImage();


        SwingUtilities.invokeLater(() -> {
            generateRandomFruits(3, yellowFruitImage, "yellow");
            generateRandomFruits(3, redFruitImage, "red");
            generateRandomFruits(3, blueFruitImage, "blue");
            generateRandomFruits(3, greenFruitImage, "green");
        });


        targetFruit = targetSequence.get(targetSequenceIndex);

        timer = new Timer(16, this);
        timer.start();
    }

    private void generateRandomFruits(int count, String imagePath, String fruitType) {
        Random random = new Random();
        int radius = 40;

        if (fruitType.equals("yellow") && yellowCount >= 3) return;
        if (fruitType.equals("red") && redCount >= 3) return;
        if (fruitType.equals("blue") && blueCount >= 3) return;
        if (fruitType.equals("green") && greenCount >= 3) return;

        for (int i = 0; i < count; i++) {
            boolean validPosition = false;
            int x = 0, y = 0;

            while (!validPosition) {
                x = random.nextInt(screenWidth - radius * 2);
                y = random.nextInt(screenHeight / 2 - radius * 2);

                Fruit newFruit = new Fruit(x, y, radius, imagePath);

                validPosition = true;
                for (Fruit fruit : fruits) {
                    if (newFruit.getBounds().intersects(fruit.getBounds())) {
                        validPosition = false;
                        break;
                    }
                }
            }

            fruits.add(new Fruit(x, y, radius, imagePath));

            if (fruitType.equals("yellow")) yellowCount++;
            if (fruitType.equals("red")) redCount++;
            if (fruitType.equals("blue")) blueCount++;
            if (fruitType.equals("green")) greenCount++;
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw fruits
        for (Fruit fruit : fruits) {
            fruit.draw(g);
        }

        // Draw shooter
        shooter.draw(g);

        // Draw projectiles
        for (Projectile projectile : projectiles) {
            projectile.draw(g);
        }

        // Target fruit text and score display
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.PLAIN, 18));
        g.drawString("Target: " + targetFruit, 10, screenHeight - 30);
        g.drawString("Score: " + score, 10, screenHeight - 10);

        int heartYPosition = screenHeight - 50 - 20;
        for (int i = 0; i < hearts; i++) {
            g.drawImage(heartImage, 10 + (i * 30), heartYPosition, 20, 20, this); // Positioning hearts
        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        for (Fruit fruit : fruits) {
            fruit.move();
            if (fruit.getX() > screenWidth) {
                fruit.resetPosition();
            }
        }

        ArrayList<Projectile> projectilesToRemove = new ArrayList<>();
        for (Projectile projectile : projectiles) {
            projectile.move();
            if (projectile.getY() < 0) {
                projectilesToRemove.add(projectile);
                hearts--;
            }
        }
        projectiles.removeAll(projectilesToRemove);

        checkProjectileCollision();

        repaint();

        // End the game rule
        if (hearts <= 0) {
            timer.stop();
            endGame();
        }
    }



    private void checkProjectileCollision() {
        ArrayList<Projectile> projectilesToRemove = new ArrayList<>();
        ArrayList<Fruit> fruitsToRemove = new ArrayList<>();

        for (Projectile projectile : projectiles) {
            for (Fruit fruit : fruits) {
                if (projectile.getBounds().intersects(fruit.getBounds()) && fruitMatchesTarget(fruit)) {
                    projectilesToRemove.add(projectile);
                    fruitsToRemove.add(fruit);

                    score += 5;

                    updateTargetFruit();
                }
            }
        }

        projectiles.removeAll(projectilesToRemove);
        fruits.removeAll(fruitsToRemove);

        if (fruits.isEmpty()) {
            resetFruitsAndSequence();
        }
    }

    private boolean fruitMatchesTarget(Fruit fruit) {
        if (targetFruit.equals("Red") && fruit.getImagePath().contains("red")) {
            return true;
        } else if (targetFruit.equals("Yellow") && fruit.getImagePath().contains("yellow")) {
            return true;
        } else if (targetFruit.equals("Blue") && fruit.getImagePath().contains("blue")) {
            return true;
        } else if (targetFruit.equals("Green") && fruit.getImagePath().contains("green")) {
            return true;
        }
        return false;
    }

    private void updateTargetFruit() {
        targetSequenceIndex = (targetSequenceIndex + 1) % targetSequence.size();
        targetFruit = targetSequence.get(targetSequenceIndex);
    }

    private void resetFruitsAndSequence() {
        yellowCount = redCount = blueCount = greenCount = 0;

        fruits.clear();
        generateRandomFruits(3, "/images/yellow_fruit.png", "yellow");
        generateRandomFruits(3, "/images/red_fruit.png", "red");
        generateRandomFruits(3, "/images/blue_fruit.png", "blue");
        generateRandomFruits(3, "/images/green_fruit.png", "green");

        targetSequenceIndex = 0;

        hearts = 5;
    }


    private void endGame() {
        timer.stop();

        frame.dispose();

        JFrame newFrame = new JFrame("Game Over");

        newFrame.setUndecorated(true);
        newFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        newFrame.setResizable(false);

        GameOverScreen gameOverScreen = new GameOverScreen(score, newFrame);

        newFrame.add(gameOverScreen);

        newFrame.setVisible(true);
    }



    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            shooter.shoot(projectiles);
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mouseDragged(MouseEvent e) {
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        shooter.setPosition(e.getX(), screenHeight - 50);
    }

    @Override
    public void doLayout() {
        screenWidth = getWidth();
        screenHeight = getHeight();
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            // Switch to the Main Menu
            MainMenu mainMenu = new MainMenu(frame);
            frame.getContentPane().removeAll();
            frame.add(mainMenu);
            frame.revalidate();
            frame.repaint();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}
