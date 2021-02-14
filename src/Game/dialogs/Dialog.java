package Game.dialogs;

import Game.MainGame;
import com.sun.tools.javac.Main;

import javax.swing.*;
import java.awt.*;

public class Dialog {
    Image image = new ImageIcon("image/Pers/nps/1/dialoge.png").getImage();
    JLabel MsgText;
    int x, y;
    boolean visible;
    String text;
    Font Msg = new Font("TimesRoman", Font.BOLD, 15);

    public Dialog(){
        MsgText = new JLabel();

    }

    public JLabel getMsgText() {
        return MsgText;
    }

    public void setMsgText(JLabel msgText) {
        MsgText = msgText;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
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
        this.y = y- image.getHeight(null);
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }
}
