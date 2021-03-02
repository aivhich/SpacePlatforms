package Game;

import Game.blaster.Blaster;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static Game.MainGame.*;

public class Alien {
    private Image image = new ImageIcon("image/alien/alien0.png").getImage();
    private int x, y;
    Timer anim, logic;
    int speed;
    boolean visible;
    Blaster blaster = new Blaster();

    Alien(int x, int y) {
        this.x = x;
        this.y = MainGame.groundY - image.getHeight(null);
        logic();
    }

    void logic(){
        logic = new Timer(50, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(MainGame.pers.inTranport||!npc[0].task) visible=false;
                else if(npc[0].task) visible=true;
                if(!visible)image = new ImageIcon("image/null.png").getImage();
                if(MainGame.pers.room==-4&&!MainGame.pers.inTranport&&visible){
                    if(MainGame.pers.getX()>x){
                        speed=1;
                        if(MainGame.pers.getX()<x+800) {
                            if(anim!=null && anim.isRunning())return;
                            anim.stop();
                            blaster.setVisible(true);
                            blaster.getShot().setVisible(true);
                            blaster.setX(x + image.getWidth(null) - 5);
                            blaster.setY(y + 12);
                            blaster.setImage(new ImageIcon("image/alien/blasterL.png").getImage());
                            blaster.blasterShot(1);
                            image = new ImageIcon("image/alien/alienShL.png").getImage();
                            logic.stop();
                        }else {
                            if(anim!=null && anim.isRunning())return;
                            Anim();
                        }
                    }else{
                        speed=-1;
                        if(MainGame.pers.getX()>x-800) {
                            if(anim!=null && anim.isRunning())return;
                            anim.stop();
                            blaster.setVisible(true);
                            blaster.getShot().setVisible(true);
                            blaster.setX(x - blaster.getImage().getWidth(null) + 5);
                            blaster.setY(y + 12);
                            blaster.setImage(new ImageIcon("image/alien/blaster.png").getImage());
                            blaster.blasterShot(-1);
                            image = new ImageIcon("image/alien/alienSh.png").getImage();
                            logic.stop();
                        }else {
                            if(anim!=null && anim.isRunning())return;
                            Anim();
                        }
                    }
                    //Anim();
                }
            }
        });
        logic.start();
    }

    int k;
    int i, cadr;
    void Anim() {
        k = 7;
        anim = new Timer(50, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               if(speed>0) image = new ImageIcon("image/alien/alienL"+i+".png").getImage();
               if(speed<0) image = new ImageIcon("image/alien/alien"+i+".png").getImage();
               if(i>7){
                   cadr++;
                   i=0;
               }
               if(cadr>7){
                   cadr=0;
                   i=0;
                   anim.stop();
               }
               if(x<500){
                   anim.stop();
                   x=500;
                   image = new ImageIcon("image/alien/alienL0.png").getImage();
               }
               x+=speed*k;
               i++;
               panel.repaint();
            }
        });
        anim.start();
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
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
}
