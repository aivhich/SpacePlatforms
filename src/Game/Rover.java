package Game;

import javax.swing.*;
import java.awt.*;

public class Rover {
    Image image = new ImageIcon("image/rover.png").getImage();
    int x=10200, y=MainGame.groundY-image.getHeight(null);
    Rover(){

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
