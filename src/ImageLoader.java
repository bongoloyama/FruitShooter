import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class ImageLoader {
    public static Image loadImage(String relativePath) {
        try {
            return ImageIO.read(ImageLoader.class.getResource(relativePath));
        } catch (IOException | NullPointerException e) {
            System.err.println("Failed to load image: " + relativePath);
            e.printStackTrace();
            return null;
        }
    }
}
