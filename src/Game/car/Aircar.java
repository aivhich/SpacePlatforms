package Game.car;

import Game.MainGame;
import Game.station.Station;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static Game.MainGame.*;

public class Aircar extends Thread {
    Image img = new ImageIcon("image/aircar/aircar.png").getImage();
    int x, y, k;
    int imax;
    int thrust=0;
    public Timer Down, start, Up, TThrust;
    boolean switchFire, switchAnim=true;
    public Engeen[] engeens = new Engeen[3];
    int speed;
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
            }
        });
        TThrust.start();
    }
    void down(){
        Down = new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (y + img.getHeight(null) + 20 < groundY) {
                    y += 8;
                    for (int w = 0; w < 3; w++) {
                        engeens[w].setY(engeens[w].getY() + 8);
                    }
                    if (thrust == 0) {
                        for (int w = 0; w < 3; w++) {
                            engeens[w].setImg(new ImageIcon("image/aircar/engeen" + 0 + ".png").getImage());
                        }
                    }
                }else{
                    if (thrust == 0) {
                        for (int w = 0; w < 3; w++)
                            engeens[w].setImg(new ImageIcon("image/aircar/engeen" + 0 + ".png").getImage());
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
    void up(){
        Up = new Timer(20, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                st++;
                y-= 1*thrust;
                for(int w =0; w<3; w++){
                    engeens[w].setY(engeens[w].getY()-(1*thrust));
                }
                if(thrust>=1){for(int w =0; w<3; w++) engeens[w].setImg(new ImageIcon("image/aircar/engeen"+st+".png").getImage());}
                if(st>=2)st=1;
                if(thrust==0){
                    if (aircar.Down != null && aircar.Down.isRunning()) return;
                    down();
                    Up.stop();
                }
                panel.repaint();
            }
        });
        Up.start();
    }
    void Collis(int x, int y, int e) {
        if ((x >= Station.getX()+40 && x+img.getWidth(null) <= (Station.getX() + Station.getImg().getWidth(null)-40))
                && (y >= Station.getY() && y <= Station.getY() + Station.getImg().getHeight(null))) {
            collis = true;
        }else{
            collis = false;
            if(speed>0 && e<0){collis=true;}
            else if(speed<0 && e>0){collis=true;}
        }
    }
}