package Game.station.detail;

import Game.MainGame;

import javax.swing.*;
import java.awt.*;

public class Solar {
    int x,y;
    Image image = new ImageIcon("image/Station/solar.png").getImage();
    int energy;
    int position;

    public Solar(int position, int x, int y){
        this.position = position;
        this.x = x;
        this.y = y;
        if(position==1||position==3){
            image = new ImageIcon("image/Station/solar2.png").getImage();
        }
    }

    public int getX() {
        return x;
    }

    public void setX(int x, int position) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
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

    public int getEnergy() {
        return energy;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }
}
