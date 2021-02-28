package Game.blaster;

import Game.MainGame;
import Game.interfase.Collision;

import javax.lang.model.AnnotatedConstruct;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Shot extends Thread  implements Collision {
    Image image = new ImageIcon("image/alien/Shot.png").getImage();
    int x, y;
    boolean visible;
    int speed, k=3;
    Timer shoting;
    int azm;

    void shot(int azm){
        speed = azm;
        shoting = new Timer(3, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                x+=speed*k;
                if(collisionShot(x, y, MainGame.pers.getX(), MainGame.pers.getY(), image, MainGame.pers.getImage(), 20)){
                    System.out.println("yes");
                    System.exit(0);
                    shoting.stop();
                }
                if(x<0&&x>MainGame.frame.getWidth()) System.exit(0);
                MainGame.panel.repaint();
            }
        });
        shoting.start();
    }

    @Override
    public void run() {
        super.run();
        shot(azm);
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
