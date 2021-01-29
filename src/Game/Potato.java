package Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Potato extends Thread{
    private Image image = new ImageIcon("image/plant/potato5.png").getImage();
    private int x, y, step=0;
    Timer GrowUp;

    public Potato(int x, int y) {
        this.x = x;
        this.y = y;
        grow();
    }
    void grow(){
        GrowUp = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                image = new ImageIcon("image/plant/potato"+step+".png").getImage();
                if(step>4){
                    step=0;
                    GrowUp.stop();
                }
                step++;
            }
        });
        GrowUp.start();
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

    public int getStep() {
        return step;
    }

    public void setStep(int step) {
        this.step = step;
    }

    public Timer getGrowUp() {
        return GrowUp;
    }

    public void setGrowUp(Timer growUp) {
        GrowUp = growUp;
    }
}
