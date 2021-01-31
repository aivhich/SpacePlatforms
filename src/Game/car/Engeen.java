package Game.car;

import Game.MainGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Engeen {
    Image img = new ImageIcon("image/aircar/engeen0.png").getImage();
    int x, y;


    public Engeen(int x, int y) {
        this.x = x;
        this.y = y-img.getHeight(null);

    }

    public Image getImg() {
        return img;
    }

    public void setImg(Image img) {
        this.img = img;
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
