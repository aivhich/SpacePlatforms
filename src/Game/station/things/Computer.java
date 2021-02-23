package Game.station.things;

import javax.swing.*;
import java.awt.*;

import static Game.MainGame.groundY;

public class Computer {
    Image image = new ImageIcon("image/computer.png").getImage();
    int x, y;
    boolean visible;
    boolean netConnection;

    String []task;

    public Computer(int x, int y) {
        this.x = x;
        this.y = groundY - image.getHeight(null)-3;
    }

    public void Visible(){
        if(!visible){
            image = new ImageIcon("null.png").getImage();
        }else{
            image = new ImageIcon("image/computer.png").getImage();
        }
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

    public String[] getTask() {
        return task;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public void setTask(String[] task) {
        this.task = task;
    }
}
