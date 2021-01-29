package Game.station;

import javax.swing.*;
import java.awt.*;

public class ModuleOxg {
    private static Image img = new ImageIcon("image/moduleOxygen.png").getImage();
    private static int x,y;

    public ModuleOxg(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public static Image getImg() {
        return img;
    }

    public void setImg(Image img) {
        this.img = img;
    }

    public static int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public static int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
