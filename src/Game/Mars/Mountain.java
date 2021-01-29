package Game.Mars;

import javax.swing.*;
import java.awt.*;

public class Mountain {
    private Image img = new ImageIcon("image/Mars/mountain0.png").getImage();
    private int x,y;
    public Mountain(int x, int y){
        this.x = x;
        this.y = y;
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
