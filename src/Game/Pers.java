package Game;

import Game.station.Station;
import com.sun.tools.javac.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static Game.MainGame.*;


public class Pers implements KeyListener {
    private static Image image = new ImageIcon("image/pers/human0.png").getImage();
    private static int x = 795, y = groundY - image.getHeight(null)-3;
    Timer anim, jump, down;
    int speed;
    boolean home = true;
    boolean Open = false;
    int Doorport = 0;    boolean inTranport = false;

    boolean collis = true;

    Pers() {
        MainGame.frame.addKeyListener(this);
    }
    public static int getX() {
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

    public static Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }


    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_D:
                if (anim != null && anim.isRunning()) return;
                Collis(x, y, 1);
                speed = 1;
                if (collis&&home) {
                    Anim();
                }else if(!home){
                    Anim();
                }
                break;
            case KeyEvent.VK_A:
                if (anim != null && anim.isRunning()) return;
                Collis(x, y, -1);
                speed = -1;
                if (collis&&home) {
                    Anim();
                }else if(!home){
                    Anim();
                }
                break;
            case KeyEvent.VK_SPACE:
                if (jump != null && jump.isRunning()) return;
                Collis(x, y, 0);
                if (collis) {
                    Jump();
                }
                break;
            case KeyEvent.VK_R:
                if (aircar.TThrust != null && aircar.TThrust.isRunning()) return;
                aircar.thrust(1);
                break;
            case KeyEvent.VK_F:
                if (aircar.TThrust != null && aircar.TThrust.isRunning()) return;
                aircar.thrust(-1);
                break;
            case KeyEvent.VK_P:
                doors();
                //aircar.CarCollis();
                break;
        }
        MainGame.panel.repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    int i = 0;
    int k = 7;
    int a = 0;
    void Anim() {
        k = 7;
        MainGame.sound.playSound("sound/steps.wav");
        //MainGame.sound.setVolume(1);
        anim = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                go();
                if(x>=frame.getWidth()){
                    speed=1;
                    refraiming();
                }else if(x<=0){
                    speed=-1;
                    refraiming();
                }
                MainGame.panel.repaint();
            }
        });
        anim.start();
        doors();
    }
    void go(){
        if(collis&&home) {
            if (speed < 0) image = new ImageIcon("image/pers/human" + i + ".png").getImage();
            if (speed > 0) image = new ImageIcon("image/pers/humanl" + i + ".png").getImage();
            i++;
            x += k * speed;
            if (i > 8) {
                i = 0;
                anim.stop();
                MainGame.sound.close();
            }
            MainGame.panel.repaint();
        }
        if(!home){

            if (speed < 0) image = new ImageIcon("image/pers/human" + i + ".png").getImage();
            if (speed > 0) image = new ImageIcon("image/pers/humanl" + i + ".png").getImage();
            i++;
            x += k * speed;
            if (i > 8) {
                i = 0;
                anim.stop();
                MainGame.sound.close();
            }
            MainGame.panel.repaint();
        }
        if(!collis&&home){
            anim.stop();
            MainGame.sound.close();
        }

    }

    int ymin = y;
    void Jump(){
        k=3;
        ymin=y;
        jump = new Timer(20, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (speed < 0) image = new ImageIcon("image/pers/human" + i + ".png").getImage();
                if (speed > 0) image = new ImageIcon("image/pers/humanl" + i + ".png").getImage();
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
                if (speed < 0) image = new ImageIcon("image/pers/human" + i + ".png").getImage();
                if (speed > 0) image = new ImageIcon("image/pers/humanl" + i + ".png").getImage();
                i++;
                if (i > 4) {
                    i = 0;
                    y += k * 3;
                }
                if(y+image.getHeight(null)<groundY-20) {
                    x += k * speed;

                }else{
                    y=groundY-image.getHeight(null);
                    down.stop();
                }
                MainGame.panel.repaint();
            }
        });
        down.start();
    }

    void Collis(int x, int y, int e) {
        if ((x >= Station.getX()+40 && x+image.getWidth(null) <= (Station.getX() + Station.getImg().getWidth(null)-40))
                && (y >= Station.getY() && y <= Station.getY() + Station.getImg().getHeight(null))) {
            collis = true;
        }else{
            collis = false;
            if(speed>0 && e<0){collis=true;}
            else if(speed<0 && e>0){collis=true;}
        }
        if(y+image.getHeight(null)<groundY){
            if (down!= null && down.isRunning()) return; Down();
        }
    }
    void doors(){
        if((x>MainGame.station.gateway[0].getX()&&x+image.getWidth(null)<MainGame.station.gateway[0].getX()+MainGame.station.gateway[0].getImage().getWidth(null))){
            Open = true;
            Doorport = -1;
        }else if((x>MainGame.station.gateway[1].getX()&&x+image.getWidth(null)<MainGame.station.gateway[1].getX()+MainGame.station.gateway[1].getImage().getWidth(null))){
            Open = true;
            Doorport = 1;
        }else{
            Open = false;
            Doorport = 0;
        }
        gateway();
    }

    void gateway() {
        if (anim != null && anim.isRunning()){
            return;
        }else{
            anim.stop();
            MainGame.sound.close();
        }
            if (Open) {
                if (Doorport < 0) {
                    x = MainGame.station.gateway[1].getX()+10;
                    home = false;
                    station.setImg(new ImageIcon("image/station2.png").getImage());
                    station.gateway[0].setImage(new ImageIcon("null.png").getImage());
                    moduleOxg.setImg(new ImageIcon("image/moduleOxygen2.png").getImage());
                } else if (Doorport > 0) {
                    x = 792;
                    home = true;
                    station.setImg(new ImageIcon("image/station1.png").getImage());
                    station.gateway[0].setImage(new ImageIcon("image/station/gatewayl.png").getImage());
                    moduleOxg.setImg(new ImageIcon("image/moduleOxygen.png").getImage());
                }
                MainGame.sound.playSound("sound/door.wav");
                Open = false;
                Doorport = 0;
            }
            panel.repaint();
        /*if(!home){
            musicpath = "music/space2.mp3";
            MainGame.music.start();
        }else{
            ap.close();
            MainGame.music.interrupt();
        }*/
    }
    void refraiming(){
        station.setX(station.getX()+frame.getWidth()*(-speed));
        moduleOxg.setX(moduleOxg.getX()+frame.getWidth()*(-speed));
        rover.setX(rover.getX()+frame.getWidth()*(-speed));
        mountain.setX(mountain.getX()+frame.getWidth()*(-speed));
        greenhouse.setX(greenhouse.getX()+frame.getWidth()*(-speed));
        for(int i=0; i<Station.gateway.length; i++) Station.gateway[i].setX(Station.gateway[i].getX()+frame.getWidth()*(-speed));
        for(int i=0; i<greenhouse.gateway.length; i++) greenhouse.gateway[i].setX(greenhouse.gateway[i].getX()+frame.getWidth()*(-speed));
        if(speed==1)x=10;
        if(speed==-1)x=frame.getWidth()- image.getWidth(null)-10;
    }
}