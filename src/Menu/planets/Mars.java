package Menu.planets;

import Game.MainGame;

import javax.swing.*;
import java.awt.*;

public class Mars {
    int x=MainGame.size.width/2-200, y=MainGame.size.height/2;
    int orbitAz=1;
    Image image= new ImageIcon("image/Planet/2.png").getImage();

    public Mars() {

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
