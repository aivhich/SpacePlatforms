package Game.station.things;

import javax.swing.*;
import java.awt.*;

import static Game.MainGame.groundY;

public class Computer {
    Image image = new ImageIcon("image/computer.png").getImage();
    int x, y;
    boolean netConnection;

    String []task;

    public Computer(int x, int y) {
        this.x = x;
        this.y = groundY - image.getHeight(null)-3;
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

    public void setTask(String[] task) {
        this.task = task;
    }
}
