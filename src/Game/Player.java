package Game;

import Game.station.Station;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static Game.MainGame.groundY;

public class Player{
    private static Image img = new ImageIcon("image/Pers/orange/humanl0.png").getImage();
    private static int x,y;
    Timer anim;
    int speed=1;
    boolean collis = false;

    Player(int x, int y) {
        this.x = x;
        this.y = y;
        Anim();
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

    public static Image getImg() {
        return img;
    }

    public void setImg(Image img) {
        this.img = img;
    }



    int i = 0;
    int k = 8;

    void Anim() {
        anim = new Timer(120, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    if (speed < 0) img = new ImageIcon("image/pers/orange/human" + i + ".png").getImage();
                    if (speed > 0) img = new ImageIcon("image/pers/orange/humanl" + i + ".png").getImage();
                    i++;
                    x += k * speed;

                    if (i > 4) {
                        i = 0;
                        anim.stop();
                    }
                MainGame.panel.repaint();
            }
        });
        anim.start();
    }

    void Collis(int x, int y, int e) {
        if ((x >= Station.getX() + 20 && x <= Station.getX() + Station.getImg().getWidth(null) - img.getWidth(null) - 20)
                && (y >= Station.getY() && y <= Station.getY() + Station.getImg().getHeight(null))) {
            collis = true;
        }else{
            collis = false;
            if(speed>0 && e<0){collis=true;}
            else if(speed<0 && e>0){collis=true;}
        }
    }
}
