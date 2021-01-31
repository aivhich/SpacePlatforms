package Game.car;

import Game.MainGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static Game.MainGame.aircar;
import static Game.MainGame.groundY;

public class Aircar {
    Image img = new ImageIcon("image/aircar/aircar.png").getImage();
    int x, y;
    int imax;
    int traction=0;
    public Timer tanim, Ttraction, gravity;
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

    int Ysp;
    int i=0;
    public void Anim() {
        tanim = new Timer(120, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (i < 3) i++;
                if(imax==1){
                    if(i==2){
                        i=3;
                    }if(i==3){
                        i=2;
                    }
                    for(int w=0; w<3; w++) engeens[w].setImg(new ImageIcon("image/aircar/engeen"+i+".png").getImage());
                }
                for(int w=0; w<3; w++) engeens[w].setImg(new ImageIcon("image/aircar/engeen"+i+".png").getImage());

                if(traction>5) Ysp = traction * -1;
                if(traction<5) Ysp = traction * 1;
                for(int w=0; w<3; w++) engeens[w].y+=Ysp;
                y += Ysp;
                if (i > 2) {
                    imax =1;
                    tanim.stop();
                    if(traction<5) {
                        if (gravity != null && gravity.isRunning()) return;
                        fizik();
                        for(int w=0; w<3; w++) engeens[w].setImg(new ImageIcon("image/aircar/engeen"+0+".png").getImage());
                    }
                }
                MainGame.panel.repaint();
            }
        });
        tanim.start();
    }
    public void traction(int az){
        Ttraction = new Timer(120, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(traction!=10&&az==1) {
                    traction++;
                }
                if(traction!=0&&az==-1) {
                    traction--;
                }
                if (aircar.tanim != null && aircar.tanim.isRunning())return;
                aircar.Anim();
                Ttraction.stop();
            }
        });
        Ttraction.start();
    }
    public void fizik(){
        gravity = new Timer(20, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(y+img.getHeight(null)<groundY){
                    y+=10;
                    i=0;
                    for(int w=0; w<3; w++) engeens[w].setImg(new ImageIcon("image/aircar/engeen"+i+".png").getImage());

                    for(int w=0; w<3; w++) engeens[w].y+=10;
                }else{
                    gravity.stop();
                }
                MainGame.panel.repaint();
            }
        });
        gravity.start();
    }
}
