package Menu.planets;

import Game.MainGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static Game.MainGame.groundY;
import static Game.MainGame.panel;

public class Mars {
    public int x=MainGame.size.width/2-200;
    public int y=MainGame.size.height/2;
    public int xB=x, yB=y;
    int orbitAz=1;
    Image image= new ImageIcon("image/Planet/2.png").getImage();
    Timer orbit;

    public Mars() {
        //orbiting();
    }

    int xf=2, a=1, c=1;

    public void orbiting(){
        orbit = new Timer(20, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                x++;
                y+= (xf^2)+a*xf+c;
                panel.repaint();
            }
        });
        orbit.start();
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

    public int getOrbitAz() {
        return orbitAz;
    }

    public void setOrbitAz(int orbitAz) {
        this.orbitAz = orbitAz;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }
}
