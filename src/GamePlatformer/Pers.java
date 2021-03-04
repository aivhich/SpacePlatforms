package GamePlatformer;

import Game.MainGame;
import Game.TwoMenu;
import Game.station.Station;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static Game.MainGame.*;


public class Pers implements KeyListener {
    private static Image image = new ImageIcon("image/pers/human0.png").getImage();
    private static int x = 795;
    public static int y = groundY - image.getHeight(null)-3;
    Timer anim, jump, down;
    int speed =0;
    public int room;
    boolean home = true;
    boolean Open = false;
    boolean reRead = false;
    boolean discuss = false;
    boolean died;
    boolean discussCollis = false;
    public int lvl = 1;
    int Doorport = 0;
    boolean inTranport = false;

    boolean Thingscollis= false;
    boolean collis = true;
    int frX, frY;

    public Pers() {
        if(lvl==0) MainGame.frame.addKeyListener(this);
        if(lvl==1) MainGamePlatform.frame.addKeyListener(this);
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

    public static Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }


    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_D:
                if(!inTranport) {
                    if (anim != null && anim.isRunning()) return;
                    if(lvl==0)Collis(x, y, 1);
                    speed = 1;
                    if(lvl==0) {
                        if (collis && home) {
                            Anim();
                        } else if (!home) {
                            Anim();
                        }
                    }else{Anim();}
                }else{
                    if (aircar.Motion != null && aircar.Motion.isRunning()) return;
                    aircar.speed = 1;
                    aircar.motion();
                }
                break;
            case KeyEvent.VK_A:
                if(!inTranport) {
                    if (anim != null && anim.isRunning()) return;
                    if(lvl==0) Collis(x, y, -1);
                    speed = -1;
                    if(lvl==0) {
                        if (collis && home) {
                            Anim();
                        } else if (!home) {
                            Anim();
                        }
                    }else{Anim();}
                }else{
                    if (aircar.Motion != null && aircar.Motion.isRunning()) return;
                    aircar.speed = -1;
                    aircar.motion();
                }
                break;
            case KeyEvent.VK_R:
                if(inTranport) {
                    if (aircar.TThrust != null && aircar.TThrust.isRunning()) return;
                    aircar.thrust(1);
                }
                break;
            case KeyEvent.VK_F:
                if(inTranport) {
                    if (aircar.TThrust != null && aircar.TThrust.isRunning()) return;
                    aircar.thrust(-1);
                }
                break;
            case KeyEvent.VK_P:
                inAircar();
                //aircar.CarCollis();
                break;
            case KeyEvent.VK_ESCAPE:
                new TwoMenu();
                break;
        }
        rePanel();
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
        anim = new Timer(50, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                go();
                if(x<-50){
                    x=MainGamePlatform.frame.getWidth()-50;
                }else if(x>MainGamePlatform.frame.getWidth()){
                    x=10;
                }
                reFrame();
                Allcolision();
                rePanel();
            }
        });
        anim.start();
    }

    void Allcolision(){
        //doors();
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
            rePanel();
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
            rePanel();
        }
        if(!collis&&home){
            anim.stop();
            MainGame.sound.close();
        }
        //System.out.println(room);
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
                rePanel();
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
                rePanel();
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
        if(y+image.getHeight(null)<groundY&&!inTranport){
            if (down!= null && down.isRunning()) return; Down();
        }
    }

    void reFrame(){
        if(lvl==0) {
            frX = frame.getWidth();
            frY = frame.getHeight();
        }
    }

    public boolean isInTranport() {
        return inTranport;
    }
    void inAircar(){
        if (anim != null && anim.isRunning()) return;
        anim.stop();
        if((!home&&!inTranport)&&Thingscollis){
            if(!pers.isInTranport()&&(aircar.speed==1||aircar.speed==0))aircar.setImg(new ImageIcon("image/aircar/aircarL0.png").getImage());
            if(!pers.isInTranport()&&aircar.speed==-1)aircar.setImg(new ImageIcon("image/aircar/aircarR0.png").getImage());
            image = new ImageIcon("image/pers/humansitR.png").getImage();
            x = aircar.getX()+49;
            y = aircar.getY()+12;
            MainGame.messageL.setText("");
            inTranport=true;
            rePanel();
        }else if(inTranport&&!home&&Thingscollis){
            if(inTranport&&aircar.speed==1)aircar.setImg(new ImageIcon("image/aircar/aircarR1.png").getImage());
            if(inTranport&&aircar.speed==-1)aircar.setImg(new ImageIcon("image/aircar/aircarL1.png").getImage());
            image = new ImageIcon("image/pers/human0.png").getImage();
            x = aircar.getX()+49;
            y = aircar.getY()+12;
            if (down!= null && down.isRunning()) return;
            speed=0;
            aircar.yspeed=3;
            aircar.thrust=0;
            Down();
            aircar.down();
            MainGame.messageL.setText("");
            inTranport=false;
        }
        if(inTranport==false){
            if(aircar.speed==1)aircar.setImg(new ImageIcon("image/aircar/aircarR1.png").getImage());
            if(aircar.speed==-1||aircar.speed==0)aircar.setImg(new ImageIcon("image/aircar/aircarL1.png").getImage());
        }
    }

    void rePanel(){
        if(lvl==0)MainGame.panel.repaint();
        if(lvl==1)MainGamePlatform.panel.repaint();
    }


    public boolean isHome() {
        return home;
    }

    public void setHome(boolean home) {
        this.home = home;
    }

    public void refraiming(){
        reFrame();
        if(!reRead) {
            if (inTranport) speed = aircar.speed;
            room += -speed;
        }else{
            speed=-room;
            reRead=false;
        }
        station.setX(station.getX()+frX*(-speed));
        moduleOxg.setX(moduleOxg.getX()+frX*(-speed));
        mountain.setX(mountain.getX()+frX*(-speed));
        greenhouse.setX(greenhouse.getX()+frX*(-speed));
        for(int i =0; i<1; i++) npc[i].setX(npc[i].getX()+frX*(-speed));
        computer.setX(computer.getX()+frX*(-speed));
        alien.setX(alien.getX()+frX*(-speed));
        if(!inTranport) {
            if(speed==1)x=10;
            if(speed==-1)x=frX- image.getWidth(null)-10;
            aircar.setX(aircar.getX() + frX * (-speed));
            for (int i = 0; i < 3; i++) aircar.engeens[i].setX(aircar.engeens[i].getX() + frX * (-speed));
        }else{
            if(aircar.speed==1)aircar.setX(10);
            if(aircar.speed==-1)aircar.setX(frX- aircar.getImg().getWidth(null)-10);
            for (int i = 0; i < 3; i++) aircar.engeens[i].setX(aircar.getX()+(85*i));
        }
        for(int i=0; i<Station.gateway.length; i++) Station.gateway[i].setX(Station.gateway[i].getX()+frX*(-speed));
    }
}