package Game.interfase;

import Game.MainGame;

import java.awt.*;

public interface Collision {
     default boolean collisionNps(int x, int y, Image img1, Image img2, int width, int height, int persX, int persY){
         if((x-width<persX&&x+img1.getWidth(null)+width>persX+img2.getWidth(null))&&(y-height<persY&&y+img1.getHeight(null)+height>persY+img2.getHeight(null)))
             return true;
         else return false;
    }
    default boolean collisionShot(int x, int y,  int persX, int persY, Image imgshot, Image img1, int width){
        if(((persX+img1.getWidth(null)+width>=x+imgshot.getWidth(null))&&(persX-width<x))&&(persY<y&&persY+img1.getHeight(null)>y)){
        return true;
        }else{return false;}
    }
    default boolean collisionPlato(int px, int py,  int persX, int persY, Image Imgplato, Image ImgP){
        if((persX>px&&persX+ImgP.getWidth(null)<px+Imgplato.getWidth(null))&&(persY<py)){
            return true;
        }else{
            return false;
        }
    }
}
