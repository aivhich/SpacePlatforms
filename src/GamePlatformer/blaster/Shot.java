package GamePlatformer.blaster;

import Game.interfase.Collision;
import GamePlatformer.MainGamePlatform;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Shot extends Thread  implements Collision {
    Image image = new ImageIcon("image/alien/Shot.png").getImage();
    int x, y;
    boolean visible;
    int speed, k=8;
    boolean status;
    volatile Timer shoting;
    int azm;


    void shot(int azm){
        speed = azm;
        shoting = new Timer(3, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                x+=speed*k;
                status=true;
                if(collisionShot(x, y, MainGamePlatform.pers.getX(), MainGamePlatform.pers.getY(), image, MainGamePlatform.pers.getImage(), 0)){
                    MainGamePlatform.pers.lvl=1;
                    visible=false;
                    status=false;
                    shoting.stop();
                }
                if(x<0||x>MainGamePlatform.frame.getWidth()+10){
                    visible=false;
                    status=false;
                    shoting.stop();
                }
                if(x<0&&x>MainGamePlatform.frame.getWidth()) System.exit(0);///?
                MainGamePlatform.panel.repaint();
            }
        });
        shoting.start();
    }

    @Override
    public void run() {
        super.run();
        shot(azm);
    }


    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean busy) {
        this.status = busy;
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

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }
}
