package Game;

import Game.interfase.Collision;

import javax.swing.*;
import java.awt.*;

import static Game.MainGame.*;

public class Rover implements Collision {
    Image image = new ImageIcon("image/rover.png").getImage();
    int x=10200, y=MainGame.groundY-image.getHeight(null);
    Rover(){

    }

    void collis(){
        if (collisionNps(x, y, image, pers.getImage(),150, 150, MainGame.pers.getX(), MainGame.pers.getY())&& !pers.Thingscollis){
            messageL.setText("Нажмите H чтобы проверить системы ровера!");
        }else{
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
