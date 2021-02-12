package Game.interfase;

import Game.MainGame;

import java.awt.*;

public interface Collision {
     default boolean collisionNps(int x, int y, Image img1, Image img2, int width, int height, int persX, int persY){
         if((x-width<persX&&x+img1.getWidth(null)+width>persX+img2.getWidth(null))&&(y-height<persY&&y+img1.getHeight(null)+height>persY+img2.getHeight(null)))
             return true;
         else return false;
    }
}
