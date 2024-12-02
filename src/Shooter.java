import java.awt.*;
import java.util.ArrayList;

public class Shooter {
    private int x, y;

    public Shooter() {
        this.x = 400;
        this.y = 550;
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void draw(Graphics g) {
        g.setColor(Color.GREEN);
        g.fillRect(x - 20, y - 10, 40, 20);
    }

    public void shoot(ArrayList<Projectile> projectiles) {
        projectiles.add(new Projectile(x, y, 10));
    }
}
