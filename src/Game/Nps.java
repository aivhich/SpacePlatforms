package Game;

import javax.swing.*;
import java.awt.*;

import static Game.MainGame.groundY;

public class Nps {
    Image image = new ImageIcon("image/Pers/nps/1/human0.png").getImage();
    int x, y;
    Nps(int x , int y){
        this.x = x;
        this.y = groundY - image.getHeight(null)-3;
    }

}
