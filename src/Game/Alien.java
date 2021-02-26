package Game;

import javax.swing.*;
import java.awt.*;

public class Alien {
    private Image image = new ImageIcon("image/alien/alien0.png").getImage();
    private int x, y;

    Alien(int x, int y) {
        this.x = x;
        this.y = MainGame.groundY - image.getHeight(null);
    }

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
