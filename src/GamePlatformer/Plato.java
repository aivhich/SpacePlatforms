package GamePlatformer;

import javax.swing.*;
import java.awt.*;

public class Plato {
    Image image = new ImageIcon("image/platforms/platform0.png").getImage();
    int x, y;

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
