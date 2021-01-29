package Game;

import Game.station.detail.Gateway;

import javax.swing.*;
import java.awt.*;

public class Greenhouse {
    private Image image = new ImageIcon("image/Greenhouse.png").getImage();
    private int x,y;
    Gateway[] gateway=new Gateway[2];
    Greenhouse(int x, int y){
        this.x = x;
        this.y = y;
        gateway[1]=new Gateway(1, x+48, y+30);
        gateway[0]=new Gateway(2, x-48, y+30);
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
