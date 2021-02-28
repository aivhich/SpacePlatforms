package Game;

import Game.car.Aircar;
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
    private static int x = 795;
    public static int y = groundY - image.getHeight(null)-3;
    Timer anim, jump, down;
    int speed;
    public int room;
    boolean home = true;
    boolean Open = false;
    boolean reRead = false;
    boolean discuss = false;
    boolean answer = false;
    boolean discussCollis = false;
    int Doorport = 0;
    boolean inTranport = false;

    boolean Thingscollis= false;
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
                    Collis(x, y, 1);
                    speed = 1;
                    if (collis && home) {
                        Anim();
                    } else if (!home) {
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
                    Collis(x, y, -1);
                    speed = -1;
                    if (collis && home) {
                        Anim();
                    } else if (!home) {
                        Anim();
                    }
                }else{
                    if (aircar.Motion != null && aircar.Motion.isRunning()) return;
                    aircar.speed = -1;
                    aircar.motion();
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
                doors();
                inAircar();
                //aircar.CarCollis();
                break;
            case KeyEvent.VK_E:
                if(discussCollis){
                    for(int i =0; i<1; i++) npc[i].discussing();
                    panel.repaint();
                }
                break;
            case KeyEvent.VK_ESCAPE:
                new TwoMenu();
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
        anim = new Timer(50, new ActionListener() {
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
        Allcolision();
    }

    void Allcolision(){
        for(int  i =0; i<1; i++) MainGame.npc[i].nameCollis();
        rover.collis();
        DoorAircar();
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
        System.out.println(room);
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
        if(y+image.getHeight(null)<groundY&&!inTranport){
            if (down!= null && down.isRunning()) return; Down();
        }
    }
    void DoorAircar(){
        if((aircar.getX()<x&&aircar.getX()+aircar.getImg().getWidth(null)>x+image.getWidth(null))&&(aircar.getY()<y&&(aircar.getY()+aircar.getImg().getHeight(null)+50)>y+ image.getHeight(null))){
            Thingscollis = true;
            MainGame.messageL.setText("Нажмите клавишу P чтобы войти/выйти!");
        }else if(Thingscollis){MainGame.messageL.setText("");Thingscollis=false; }
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
            panel.repaint();
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

    void doors(){
        if((x>MainGame.station.gateway[0].getX()&&x+image.getWidth(null)<MainGame.station.gateway[0].getX()+MainGame.station.gateway[0].getImage().getWidth(null)+200)){
            Open = true;
            Doorport = -1;
        }else if((x>MainGame.station.gateway[1].getX()-200&&x+image.getWidth(null)<MainGame.station.gateway[1].getX()+MainGame.station.gateway[1].getImage().getWidth(null))){
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
            if (Open&&!inTranport) {
                if (Doorport < 0) {
                    x = MainGame.station.gateway[1].getX()+10;
                    home = false;
                    reHome();
                    for(int i =0; i<1; i++){
                        npc[i].setVisible(false);
                        computer.setVisible(false);
                        npc[i].Visible();
                        computer.Visible();
                    }
                } else if (Doorport > 0) {
                    x = 792;
                    home = true;
                    reHome();
                    for(int i =0; i<1; i++){
                        npc[i].setVisible(true);
                        computer.setVisible(true);
                        npc[i].Visible();
                        computer.Visible();
                    }
                }
                MainGame.sound.playSound("sound/door.wav");
                Open = false;
                Doorport = 0;
            }
            panel.repaint();
    }
    void reHome(){
        if(home) {
            station.setImg(new ImageIcon("image/station1.png").getImage());
            station.gateway[0].setImage(new ImageIcon("image/station/gatewayl.png").getImage());
            moduleOxg.setImg(new ImageIcon("image/moduleOxygen.png").getImage());
            computer.Visible();
            for(int i = 0; i<1; i++)npc[i].setVisible(true);npc[i].Visible();
        }else{
            station.setImg(new ImageIcon("image/station2.png").getImage());
            station.gateway[0].setImage(new ImageIcon("image/null.png").getImage());
            moduleOxg.setImg(new ImageIcon("image/moduleOxygen2.png").getImage());
            computer.Visible();
            for(int i = 0; i<1; i++)npc[i].setVisible(false);npc[i].Visible();
        }
    }

    public boolean isHome() {
        return home;
    }

    public void setHome(boolean home) {
        this.home = home;
    }

    public void refraiming(){
        if(!reRead) {
            if (inTranport) speed = aircar.speed;
            room += -speed;
        }else{
            speed=-room;
            reRead=false;
        }
        station.setX(station.getX()+frame.getWidth()*(-speed));
        moduleOxg.setX(moduleOxg.getX()+frame.getWidth()*(-speed));
        rover.setX(rover.getX()+frame.getWidth()*(-speed));
        mountain.setX(mountain.getX()+frame.getWidth()*(-speed));
        greenhouse.setX(greenhouse.getX()+frame.getWidth()*(-speed));
        for(int i =0; i<1; i++) npc[i].setX(npc[i].getX()+frame.getWidth()*(-speed));
        computer.setX(computer.getX()+frame.getWidth()*(-speed));
        alien.setX(alien.getX()+frame.getWidth()*(-speed));
        if(!inTranport) {
            if(speed==1)x=10;
            if(speed==-1)x=frame.getWidth()- image.getWidth(null)-10;
            aircar.setX(aircar.getX() + frame.getWidth() * (-speed));
            for (int i = 0; i < 3; i++) aircar.engeens[i].setX(aircar.engeens[i].getX() + frame.getWidth() * (-speed));
        }else{
            if(aircar.speed==1)aircar.setX(10);
            if(aircar.speed==-1)aircar.setX(frame.getWidth()- aircar.getImg().getWidth(null)-10);
            for (int i = 0; i < 3; i++) aircar.engeens[i].setX(aircar.getX()+(85*i));
        }
        for(int i=0; i<Station.gateway.length; i++) Station.gateway[i].setX(Station.gateway[i].getX()+frame.getWidth()*(-speed));
        for(int i=0; i<greenhouse.gateway.length; i++) greenhouse.gateway[i].setX(greenhouse.gateway[i].getX()+frame.getWidth()*(-speed));
    }

    void saves(Boolean read){
        if(read){
            x = Integer.parseInt(String.valueOf(Savedata.readlist.get(0)));
            y = Integer.parseInt(String.valueOf(Savedata.readlist.get(1)));
            home = Boolean.parseBoolean(String.valueOf(Savedata.readlist.get(2)));
            inTranport = Boolean.parseBoolean(String.valueOf(Savedata.readlist.get(3)));
            room = Integer.parseInt(String.valueOf(Savedata.readlist.get(4)));
            reRead=true;
            refraiming();
            reHome();
        }else{
            Savedata.list.add(x);
            Savedata.list.add(y);
            Savedata.list.add(home);
            Savedata.list.add(inTranport);
            Savedata.list.add(room);
        }
    }
}