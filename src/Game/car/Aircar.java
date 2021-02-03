package Game.car;

import Game.MainGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static Game.MainGame.*;

public class Aircar {
    Image img = new ImageIcon("image/aircar/aircar.png").getImage();
    int x, y;
    int imax;
    int mass = 2;
    int traction=0;
    public Timer drop, start, gravity, TThrust;
    boolean switchFire;
    public Engeen[] engeens = new Engeen[3];


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

    int St;
    void startUp(){
        start = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(traction>=5) {
                    St=1;
                }
                St++;
                for(int w=0; w<3; w++){
                    engeens[w].setImg(new ImageIcon("image/aircar/engeen"+St+".png").getImage());
                }
                if(traction>3){
                    y-=traction;
                    for(int w=0; w<3; w++)engeens[w].setY(engeens[w].getY()-1*traction);
                }else {
                    y+=traction;
                    for(int w=0; w<3; w++)engeens[w].setY(engeens[w].getY()+1*traction);
                }
                if(St>=3){
                    if(traction>=5) {
                        St = 1;
                    }else {
                        St = 0;
                    }
                    start.stop();
                }
                if(traction==0){
                    start.stop();
                    drop();
                }
                panel.repaint();
            }
        });
        start.start();
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
                if (aircar.start != null && aircar.start.isRunning()) return;
                startUp();
            }
        });
        TThrust.start();
    }
    void drop(){
        drop = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(y+img.getHeight(null)<=groundY) {
                    y += (mass*1);
                    for (int w = 0; w < 3; w++) engeens[w].setY(engeens[w].getY() + (mass*1));
                    for (int w = 0; w < 3; w++)engeens[w].setImg(new ImageIcon("image/aircar/engeen"+0+".png").getImage());
                }else{
                    drop.stop();
                }
                panel.repaint();
            }
        });
        drop.start();
    }
}
