package GamePlatformer;

import Game.MainGame;
import GamePlatformer.blaster.Blaster;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static Game.MainGame.npc;
import static Game.MainGame.panel;

public class AlienP extends Thread {
    private Image image = new ImageIcon("image/alien/alien0.png").getImage();
    private int x, y;
    Timer anim, logic;
    int speed;
    boolean visible;
    Blaster blaster = new Blaster();

    AlienP(int x, int y) {
        this.x = x;
        this.y = MainGamePlatform.groundY - image.getHeight(null);
    }

    void logic(){
        logic = new Timer(50, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if ((x-300>MainGamePlatform.pers.getX()&&(x-600<MainGamePlatform.pers.getX()))||(x+300<MainGamePlatform.pers.getX()&&x+600>MainGamePlatform.pers.getX())) {
                    if (x > MainGamePlatform.pers.getX()) {
                        speed = -1;
                    } else if (x < MainGamePlatform.pers.getX()) {
                        speed = 1;
                    }
                    blaster.setVisible(false);
                    blaster.shot.setVisible(false);
                    if (anim != null && anim.isRunning()) return;
                    Anim();
                }else {
                    if ((x + 350 >= MainGamePlatform.pers.getX())&&(x<MainGamePlatform.pers.getX()) && (!blaster.getShot().getStatus())) {
                        strike(1);
                    } else if (x - 350 <= MainGamePlatform.pers.getX()+MainGamePlatform.pers.getImage().getWidth(null)&&(x>MainGamePlatform.pers.getX()) && (!blaster.getShot().getStatus())) {
                        strike(-1);
                    }
                    if (anim != null && anim.isRunning()) anim.stop();
                }

                MainGamePlatform.panel.repaint();
            }
        });
        logic.start();
    }

    @Override
    public void run() {
        if(logic!=null&& logic.isRunning())return;
        logic();
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
               MainGamePlatform.panel.repaint();
            }
        });
        anim.start();
    }


    void strike(int azm){
        blaster.setVisible(true);
        blaster.getShot().setVisible(true);
        blaster.getShot().setVisible(true);
        if(azm>0){
            blaster.setX(x + blaster.getImage().getWidth(null) + 5);
            blaster.setY(y + 12);
            blaster.setImage(new ImageIcon("image/alien/blasterL.png").getImage());
            image = new ImageIcon("image/alien/alienShL.png").getImage();
        }
        else if(azm<0){
            blaster.setX(x - blaster.getImage().getWidth(null) + 5);
            blaster.setY(y + 12);
            blaster.setImage(new ImageIcon("image/alien/blaster.png").getImage());
            image = new ImageIcon("image/alien/alienSh.png").getImage();
        }
        blaster.blasterShot(azm);
        MainGamePlatform.panel.repaint();
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

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public void setX(int x) {
        this.x = x;
    }

    public Blaster getBlaster() {
        return blaster;
    }

    public void setBlaster(Blaster blaster) {
        this.blaster = blaster;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
