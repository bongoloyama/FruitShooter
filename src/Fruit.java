import java.awt.*;

public class Fruit {
    private int x, y, radius;
    private String imagePath;
    private Image image;

    private final int speed = 5;

    public Fruit(int x, int y, int radius, String imagePath) {
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.imagePath = imagePath;
        this.image = ImageLoader.loadImage(imagePath);
    }

    public void draw(Graphics g) {
        if (image != null) {
            g.drawImage(image, x, y, radius * 2, radius * 2, null);
        } else {
            g.setColor(Color.GRAY);
            g.fillOval(x, y, radius * 2, radius * 2);
        }
    }

    public void move() {
        x += speed; // Move the fruit to the right
    }

    public void resetPosition() {
        x = -radius * 2; // Move the fruit back to the left
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, radius * 2, radius * 2);
    }

    public String getImagePath() {
        return imagePath;
    }
}
