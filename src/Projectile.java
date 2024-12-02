import java.awt.*;

public class Projectile {
    private int x, y;
    private int size;
    private final int speed = 10;

    public Projectile(int startX, int startY, int size) {
        this.x = startX - size / 2;
        this.y = startY - size;
        this.size = size;
    }

    public void move() {
        y -= speed;
    }

    public void draw(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillOval(x, y, size, size);
    }

    public int getY() {
        return y;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, size, size);
    }
}
