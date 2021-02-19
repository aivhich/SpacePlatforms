package Menu.planets;

import javax.swing.*;
import java.awt.*;

public class Earth {
    int x, y;
    int orbitAz=0;
    Image image= new ImageIcon("image/Planet/3.png").getImage();

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

    public int getOrbitAz() {
        return orbitAz;
    }

    public void setOrbitAz(int orbitAz) {
        this.orbitAz = orbitAz;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }
}
