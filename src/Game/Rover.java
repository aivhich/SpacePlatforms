package Game;

import Game.interfase.Collision;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static Game.MainGame.*;

public class Rover extends Thread implements Collision{
    Image image = new ImageIcon("image/rover.png").getImage();
    int x= frame.getWidth()*5-400, y=MainGame.groundY-image.getHeight(null);
    boolean visible=false;
    Timer Logic;


    Rover(){

    }

    void logic(){
        Logic = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!visible)image = new ImageIcon("image/null.png").getImage();
                else if(visible) image = new ImageIcon("image/rover.png").getImage();
                if(npc[0].task) visible=true;
                else{ visible=false;}
            }
        });
        Logic.start();
    }

    @Override
    public void run() {
        super.run();
        logic();
    }

    void collis(){
        if (collisionNps(x, y, image, pers.getImage(),150, 150, MainGame.pers.getX(), MainGame.pers.getY())&& !pers.discussCollis){
            messageL.setText("Нажмите H чтобы проверить системы ровера!");
        }else if(!pers.discussCollis&& !MainGame.pers.Thingscollis){
            messageL.setText("");
        }
        panel.repaint();
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
