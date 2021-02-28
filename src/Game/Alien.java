package Game;

import Game.blaster.Blaster;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static Game.MainGame.frame;
import static Game.MainGame.panel;

public class Alien {
    private Image image = new ImageIcon("image/alien/alien0.png").getImage();
    private int x, y;
    Timer anim, logic;
    int speed;
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
                if(MainGame.pers.room==-6&&x>500&&!MainGame.pers.inTranport){
                    if(anim!=null && anim.isRunning())return;
                    speed=-1;
                    if(MainGame.pers.getX()>x){
                        blaster.blasterShot(1);
                        image = new ImageIcon("image/alien/alienSh.png").getImage();
                        blaster.setX(x);
                        blaster.setY(y+50);
                    }else{
                        blaster.blasterShot(-1);
                        image = new ImageIcon("image/alien/alienShL.png").getImage();
                        blaster.setX(x+ image.getWidth(null));
                        blaster.setY(y+50);
                    }
                    Anim();
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
               if(i>8){
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
