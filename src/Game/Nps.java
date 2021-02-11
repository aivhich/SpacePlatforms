package Game;

import javax.swing.*;
import java.awt.*;

import static Game.MainGame.groundY;

public class Nps {
    Image image = new ImageIcon("image/Pers/nps/1/human0.png").getImage();
    private int type, x, y;
    private String name;
    public static JLabel Lstr;

    Nps(String name, int type, int x , int y){
        Lstr = new JLabel();
        this.name = name;
        this.type = type;
        this.x = x;
        this.y = groundY - image.getHeight(null)-3;
        MainGame.panel.add(Lstr);
        Lstr.setForeground(Color.WHITE);
    }

    void nameCollis(){
        if((x-100<MainGame.pers.getX()&&x+100>MainGame.pers.getX())&&(y-100<MainGame.pers.getY()&&y+100>MainGame.pers.getY())){
            Lstr.setText(name);
            Lstr.setBounds(x, y-30,100,40);
        }else{
            Lstr.setText("");
        }
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
