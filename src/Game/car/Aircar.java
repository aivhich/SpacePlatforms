package Game.car;

import Game.MainGame;
import Game.Pers;
import Game.station.Station;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static Game.MainGame.*;

public class Aircar extends Thread {
    Image img = new ImageIcon("image/aircar/aircar1.png").getImage();
    int x, y, k;
    int imax;
    int thrust=0;
    boolean inPilot;
    public Timer Down, start, Up, TThrust;
    boolean switchFire, switchAnim=true;
    public Engeen[] engeens = new Engeen[3];
    int speed, yspeed;
    boolean collis;


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
        if(thrust>0) {
            MainGame.sound.playSound("sound/engeenfire.wav");
        }
        TThrust = new Timer(80, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if((thrust<10&&k==1)||(thrust>0&&k==-1)) {
                    thrust += k;
                }
                MainGame.thrustL.setText("Thrust: "+thrust);
                TThrust.stop();
                if (aircar.Up != null && aircar.Up.isRunning()) return;
                up();
                if(thrust>0) {
                    MainGame.sound.playSound("sound/engeenfire.wav");
                }
            }
        });
        TThrust.start();
    }
    void down(){
        Down = new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (y + img.getHeight(null) + 20 < groundY) {
                    y += 1+yspeed;
                    for (int w = 0; w < 3; w++) engeens[w].setY(engeens[w].getY() + (1+yspeed));
                    yspeed+=1;
                    if (thrust == 0) {
                        for (int w = 0; w < 3; w++) {
                            engeens[w].setImg(new ImageIcon("image/aircar/engeen" + 0 + ".png").getImage());
                        }
                    }
                }else{
                    y=groundY-img.getHeight(null)-20;
                    for (int w = 0; w < 3; w++) engeens[w].setY(y+img.getHeight(null)-10);
                    if (thrust == 0) {
                        yspeed =0;
                        for (int w = 0; w < 3; w++) engeens[w].setImg(new ImageIcon("image/aircar/engeen" + 0 + ".png").getImage());
                        if (aircar.Up != null && aircar.Up.isRunning()) return;
                        Up.stop();
                    }
                    Down.stop();
                }
                panel.repaint();
            }
        });
        Down.start();
    }
    int st;
    int nCadr = 0;
    void up(){
        Up = new Timer(20, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (aircar.Down != null && aircar.Down.isRunning()) Down.stop();
                if(thrust>2) {
                    y -= (1 * thrust);
                    for (int w = 0; w < 3; w++) engeens[w].setY(engeens[w].getY() - ((1 * thrust)));
                }else if((thrust<2)&&(y + img.getHeight(null) + 20 < groundY)){
                    y -= (-1 * thrust);
                    for (int w = 0; w < 3; w++) engeens[w].setY(engeens[w].getY() - ((-1 * thrust)));
                }
                if(thrust>=1){for(int w =0; w<3; w++) engeens[w].setImg(new ImageIcon("image/aircar/engeen"+st+".png").getImage());}
                if(thrust==0){
                    if (aircar.Down != null && aircar.Down.isRunning()) return;
                    down();
                    MainGame.sound.close();
                    Up.stop();
                }
                if(nCadr>=10){nCadr=0; st++;}
                else{nCadr++;}
                if(st>2){st=1;nCadr=0;}
                panel.repaint();
            }
        });
        Up.start();
    }
}