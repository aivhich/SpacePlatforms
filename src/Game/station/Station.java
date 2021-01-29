package Game.station;

import Game.MainGame;
import Game.Pers;
import Game.station.detail.Gateway;

import javax.swing.*;
import java.awt.*;

public class Station {
    private static int x, y;
    private static Image img = new ImageIcon("image/station1.png").getImage();
    public static Gateway[] gateway =new Gateway[2];

    public Station(int x, int y) {
        this.x = x;
        this.y = y;
        gateway[1] = new Gateway(1, x + img.getWidth(null)+MainGame.moduleOxg.getImg().getWidth(null)-2, MainGame.groundY - (img.getHeight(null) / 2 - 10));
        gateway[0] = new Gateway(2, x + img.getWidth(null)-105, MainGame.groundY - (img.getHeight(null) / 2 - 10));
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

    public static Image getImg() {
        return img;
    }

    public void setImg(Image img) {
        this.img = img;
    }
}
