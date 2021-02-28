package Game.blaster;

import Game.MainGame;

import javax.lang.model.AnnotatedConstruct;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Shot {
    Image image = new ImageIcon("image/alien/Shot.png").getImage();
    int x, y;
    boolean visible;
    int speed, k=12;
    Timer shoting;

    void shot(int Bx, int By, int azm){
        x=Bx; y = By;
        shoting = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                x+=speed*k;
                if(x> MainGame.frame.getWidth()||x<0){
                    visible=false;
                    shoting.stop();
                    System.out.print("stop");
                }
            }
        });
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
