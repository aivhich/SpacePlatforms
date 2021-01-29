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
    Timer anim, jump, down;
    int speed;
    boolean collis = true;

    Player(int x, int y) {
        this.x = x;
        this.y = y;
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
                MainGame.panel.repaint();

                if (i > 4) {
                    i = 0;
                    anim.stop();
                }
            }
        });
        anim.start();
    }

    int ymin = y;
    int a=0;
    void Jump(){
        k=3;
        ymin=y;
        jump = new Timer(20, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (speed < 0) img = new ImageIcon("image/pers/human" + i + ".png").getImage();
                if (speed > 0) img = new ImageIcon("image/pers/humanl" + i + ".png").getImage();
                a++;
                x += k * speed;
                y += k*-2;
                MainGame.panel.repaint();
                if (a > 4) {
                    a =0;
                    i+=1;
                }
                if(y<ymin-80){
                    jump.stop();
                    if (down!= null && down.isRunning()) return; Down();
                }
            }
        });
        jump.start();
    }
    void Down(){
        k=4;
        down = new Timer(20, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (speed < 0) img = new ImageIcon("image/pers/human" + i + ".png").getImage();
                if (speed > 0) img = new ImageIcon("image/pers/humanl" + i + ".png").getImage();
                i++;
                if (i > 4) {
                    i = 0;
                    y += k * 3;
                }
                if(y+img.getHeight(null)<groundY-20) {
                    x += k * speed;

                }else{
                    y=groundY-img.getHeight(null);
                    down.stop();
                }
                MainGame.panel.repaint();
            }
        });
        down.start();
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
        if(y+img.getHeight(null)<groundY){
            if (down!= null && down.isRunning()) return; Down();
        }
    }
}
