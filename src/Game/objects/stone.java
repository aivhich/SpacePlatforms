package Game.objects;

import javax.swing.*;
import java.awt.*;

public class stone {
    int x, y;
    Image image = new ImageIcon("image/Mars/stone.png").getImage();

    public stone(int x, int y, Image image) {
        this.x = x;
        this.y = y;
    }
}
