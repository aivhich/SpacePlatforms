package Game.station.detail;


import Game.Pers;

import javax.swing.*;
import java.awt.*;

public class Gateway {
    int x,y, position;
    Image image = new ImageIcon("image/Station/gateway.png").getImage();

    public Gateway(int position, int x, int y){
        this.position = position;
        this.x = x;
        this.y = y;
        if(position==2){
            image = new ImageIcon("image/Station/gatewayl.png").getImage();
        }
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

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }
}
