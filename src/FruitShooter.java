import javax.swing.*;

public class FruitShooter {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Fruit Shooter");

            new IntroScreen(frame);
        });
    }
}
