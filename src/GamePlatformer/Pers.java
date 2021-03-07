package GamePlatformer;

import Game.MainGame;
import Game.TwoMenu;
import Game.interfase.Collision;
import Game.station.Station;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static Game.MainGame.*;


public class Pers implements KeyListener, Collision {
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

    int platoI=-1;
    boolean Thingscollis= false;
    boolean collis = true;
    int frX, frY, xf=1;

    public Pers() {
        MainGamePlatform.frame.addKeyListener(this);
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

                    speed = 1;
                    if(lvl==0) {
                        if (collis && home) {
                            Anim();
                        } else if (!home) {
                            Anim();
                        }
                    }else{
                        Anim();
                    }
                }else{
                    if (aircar.Motion != null && aircar.Motion.isRunning()) return;
                    aircar.speed = 1;
                    aircar.motion();
                }
                break;
            case KeyEvent.VK_A:
                if(!inTranport) {
                    if (anim != null && anim.isRunning()) return;
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
            case KeyEvent.VK_W:
                if ((jump != null && jump.isRunning())||(down != null && down.isRunning())) return;
                Jump();
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
                if((x<-50)&&speed<0){
                    x=MainGamePlatform.frame.getWidth()-50;
                    refraiming();
                }else if((x>MainGamePlatform.frame.getWidth())&&speed>0){
                    x=10;
                    refraiming();
                }
                if((y+image.getHeight(null)<MainGamePlatform.groundY)&&platoI>=0)
                {
                    for(int  i =0; i<20; i++) {
                        if(!collisionPlato(MainGamePlatform.plX[i], MainGamePlatform.plY[i], x, y, MainGamePlatform.plato[i].getImage(), image)){
                            if (down != null && down.isRunning()) return;
                            Down();
                        }
                    }
                }else if(platoI>=0&&(y+image.getHeight(null)>MainGamePlatform.groundY)){
                    y=MainGamePlatform.groundY-image.getHeight(null);
                    platoI=-1;
                }
                reFrame();
                rePanel();
            }
        });
        anim.start();
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
                x += k * speed;
                y += k*-2;
                if(y<ymin-200){
                    jump.stop();
                    if (down!= null && down.isRunning()) return;
                    Down();
                }
                MainGamePlatform.panel.repaint();
            }
        });
        jump.start();
    }
    void Down(){
        k=4;
        down = new Timer(20, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                x += k * speed;
                y += k * 3;
                if((y+100)>MainGamePlatform.groundY+20){
                    down.stop();
                }
                for(int  i =0; i<20; i++) {
                    if(collisionPlato(MainGamePlatform.plX[i], MainGamePlatform.plY[i], x, y, MainGamePlatform.plato[i].getImage(), image)){
                        if((MainGamePlatform.plY[i]<y+image.getHeight(null)+10)&&y+image.getHeight(null)<MainGamePlatform.groundY){
                            platoI=i;
                            y=MainGamePlatform.plY[i]- image.getHeight(null);
                            down.stop();
                        }else if(y+image.getHeight(null)>=MainGamePlatform.groundY){
                            platoI=-1;
                        }
                    }
                }
                MainGamePlatform.panel.repaint();
            }
        });
        down.start();
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
        for(int i=0; i<20; i++){
            MainGamePlatform.plato[i].setX(MainGamePlatform.plato[i].getX()-(MainGamePlatform.frame.getWidth()*(speed)));
            MainGamePlatform.panel.repaint();
        }
    }
}