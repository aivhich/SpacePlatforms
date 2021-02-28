package Game.blaster;

import Game.MainGame;

import javax.swing.*;
import java.awt.*;

public class Blaster {
    Image image = new ImageIcon("image/alien/blaster.png").getImage();
    int x, y;
    boolean visible;
    Shot shot = new Shot();

    public void blasterShot(int azm){
        if(shot.shoting!=null && shot.shoting.isRunning())return;
        System.out.println("starting");
        shot.setX(x);
        shot.setY(y);
        shot.azm=azm;
        shot.run();
        MainGame.panel.repaint();
    }

    public Shot getShot() {
        return shot;
    }

    public void setShot(Shot shot) {
        this.shot = shot;
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

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }
}
