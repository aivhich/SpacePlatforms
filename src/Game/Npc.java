package Game;

import Game.dialogs.Dialog;
import Game.interfase.Collision;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static Game.MainGame.*;

public class Npc extends Thread implements Collision{
    Image image = new ImageIcon("image/Pers/nps/1/humanl8.png").getImage();
    private int type, x, y;
    private String name, rank;
    public static JLabel Lstr;
    Timer anim, Logic;
    boolean task = false;

    private boolean visible=true;
    int ValDialog = 0;
    public static Game.dialogs.Dialog dialog;
    boolean home, collis, busy;
    static int speed=2;

    Npc(String name, String rank, int type, int x , int y){
        dialog = new Dialog();
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
                Visible();
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
        for(int  i =0; i<1; i++) MainGame.npc[i].nameCollis();
    }

    void discussing(){
        dialog.setX(x+image.getWidth(null));
        dialog.setY(y);
        if(ValDialog==0)dialog.getMsgText().setText("<html>Задание:<br>Найдите ровер и почините его.</html>");
        if(ValDialog==0) tasksL.setText("<html>Задание:<br>Найдите ровер и почините его. Он находиься в правой стороне, нашего двумерного мира.</html>");
        task=true;
        dialog.getMsgText().setBounds(dialog.getX()+5, dialog.getY()-5, 190, 106);
        dialog.setVisible(true);
        panel.repaint();
        ValDialog++;
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
        if (collisionNps(x, y, image, pers.getImage(),150, 150, MainGame.pers.getX(), MainGame.pers.getY())&& !pers.Thingscollis){
            Lstr.setText("<html>"+name+"<br>"+rank+"</html>");
            Lstr.setBounds(x, y-50,100,40);
            messageL.setText("Нажмите на E чтобы говорить!");
            pers.discussCollis = true;
            panel.repaint();
        }else{
            pers.discussCollis = false;
            messageL.setText("");
            dialog.setVisible(false);
            dialog.getMsgText().setText("");
            Lstr.setText("");
        }
        panel.repaint();
    }

    public static Dialog getDialog() {
        return dialog;
    }

    public static void setDialog(Dialog dialog) {
        Npc.dialog = dialog;
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

    void Visible(){
        if(!visible){
            image = new ImageIcon("null.png").getImage();
        }else{
            image = new ImageIcon("image/Pers/nps/1/humanl8.png").getImage();
        }
    }

    public boolean isVisible() {
        return visible;
    }

    public boolean isTask() {
        return task;
    }

    public void setTask(boolean task) {
        this.task = task;
    }


    public void setVisible(boolean visible) {
        this.visible = visible;
    }
}
