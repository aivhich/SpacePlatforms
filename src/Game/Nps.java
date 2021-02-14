package Game;

import Game.interfase.Collision;
import Game.station.Station;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static Game.MainGame.*;

public class Nps extends Thread implements Collision{
    Image image = new ImageIcon("image/Pers/nps/1/humanl8.png").getImage();
    private int type, x, y;
    private String name, rank;
    public static JLabel Lstr;
    Timer anim, Logic;
    boolean home, collis, busy;
    static int speed=2;

    Nps(String name, String rank, int type, int x , int y){
        Lstr = new JLabel();
        this.name = name;
        this.rank = rank;
        this.type = type;
        this.x = x;
        this.y = groundY - image.getHeight(null)-3;
        MainGame.panel.add(Lstr);
        Lstr.setForeground(Color.WHITE);
    }
    int i = 8;
    int k = 7;

    void logic(){
        Logic = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(i<=6) i=8;
                speed = -speed;
                y+=speed;
                image = new ImageIcon("image/Pers/nps/1/humanl"+i+".png").getImage();
                i-=1;
                panel.repaint();
            }
        });
        Logic.start();
    }

    @Override
    public void run() {
        super.run();
        logic();
    }

    void Anim() {
        k = 7;
        //MainGame.sound.playSound("sound/steps.wav");
        //MainGame.sound.setVolume(1);
        anim = new Timer(50, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                go();
                if(x>=frame.getWidth()){
                    speed=1;
                }else if(x<=0){
                    speed=-1;
                }
                MainGame.panel.repaint();
            }
        });
        anim.start();
        for(int  i =0; i<1; i++) MainGame.nps[i].nameCollis();
    }

    void go() {
        if (collis && home) {
            if (speed < 0) image = new ImageIcon("image/Pers/nps/1/human" + i + ".png").getImage();
            if (speed > 0) image = new ImageIcon("image/Pers/nps/1/humanl" + i + ".png").getImage();
            i++;
            x += k * speed;
            if (i > 8) {
                i = 0;
                anim.stop();
                MainGame.sound.close();
            }
            MainGame.panel.repaint();
        }
        if (!home) {
            if (speed < 0) image = new ImageIcon("image/Pers/nps/1/human" + i + ".png").getImage();
            if (speed > 0) image = new ImageIcon("image/Pers/nps/1/humanl" + i + ".png").getImage();
            i++;
            x += k * speed;
            if (i > 8) {
                i = 0;
                anim.stop();
                MainGame.sound.close();
            }
            MainGame.panel.repaint();
        }
        if (!collis && home) {
            anim.stop();
            MainGame.sound.close();
        }
        if (collisionNps(x, y, image, computer.getImage(), 50, 50, computer.getX(), computer.getY())) {
            anim.stop();
            computer.getX();
        }
    }

    void nameCollis(){
        if (collisionNps(x, y, image, pers.getImage(),100, 100, MainGame.pers.getX(), MainGame.pers.getY())){
            Lstr.setText("<html>"+name+"<br>"+rank+"</html>");
            Lstr.setBounds(x, y-50,100,40);
            messageL.setText("Нажмите на E чтобы говорить!");
            panel.repaint();
        }else{
            Lstr.setText("");
            messageL.setText("");
        }
        panel.repaint();
    }

    public static JLabel getLstr() {
        return Lstr;
    }

    public static void setLstr(JLabel lstr) {
        Lstr = lstr;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
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
