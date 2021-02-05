package Game.car;

import Game.MainGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static Game.MainGame.*;

public class Aircar {
    Image img = new ImageIcon("image/aircar/aircar.png").getImage();
    int x, y, k;
    int imax;
    int traction=0;
    public Timer Down, start, Up, TThrust;
    boolean switchFire, switchAnim=true;
    public Engeen[] engeens = new Engeen[3];
    int Gup, Gdown;


    public Aircar(int x, int y) {
        this.x = x;
        this.y = y-img.getHeight(null)-10;

        for(int e =0; e<3; e++){
            engeens[e] = new Engeen(this.x+(85*e), this.y+img.getHeight(null)+20);
        }

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

    public void thrust(int k){
        TThrust = new Timer(80, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if((traction<10&&k==1)||(traction>0&&k==-1)) {
                    traction += k;
                }
                MainGame.thrustL.setText("Thrust: "+traction);
                TThrust.stop();
                if (aircar.Up != null && aircar.Up.isRunning()) return;
                up();
            }
        });
        TThrust.start();
    }
    void down(){
        if(y+img.getHeight(null)+20<groundY){
            y+=4;
            for(int w =0; w<3; w++){
                engeens[w].setY(engeens[w].getY()+4);
            }
            if(traction<=1) {
                for (int w = 0; w < 3; w++) {
                    engeens[w].setImg(new ImageIcon("image/aircar/engeen" + 0 + ".png").getImage());
                }
            }
        }else{
            if(traction<=1) {
                for (int w = 0; w < 3; w++) engeens[w].setImg(new ImageIcon("image/aircar/engeen" + 0 + ".png").getImage());
                if (aircar.Up != null && aircar.Up.isRunning()) return;
                Up.stop();
            }
        }
    }
    int st;
    void up(){
        Up = new Timer(50, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                st++;
                y-= 1*traction;
                for(int w =0; w<3; w++){
                    engeens[w].setY(engeens[w].getY()-(1*traction));
                }
                for(int w =0; w<3; w++) engeens[w].setImg(new ImageIcon("image/aircar/engeen"+st+".png").getImage());
                if(st>=2)st=1;
                down();
                panel.repaint();
            }
        });
        Up.start();
    }
}
