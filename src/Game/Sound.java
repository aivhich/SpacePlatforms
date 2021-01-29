package Game;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class Sound {
    private static boolean released = false;
    private static boolean playing = false;
    private  static AudioInputStream stream = null;
    private static Clip clip = null;

    public Sound() {
    }

    public Sound(File file) {
        try {
            stream = AudioSystem.getAudioInputStream(file);
            clip = AudioSystem.getClip();
            clip.open(stream);
            released = true;

        }catch (IOException | UnsupportedAudioFileException | LineUnavailableException exception){
            exception.printStackTrace();
            released = false;
        }
    }

    public static boolean isPlaying() {
        return playing;
    }

    public void play(boolean breakOld){
        if(released){
            if(breakOld){
                clip.stop();
                clip.setFramePosition(0);
                clip.start();
                playing= true;
            }else if(!isPlaying()){
                clip.setFramePosition(0);
                clip.start();
                playing = false;
            }
        }
    }
    public void play(){
        play(true);
    }
    public static void stop(){
        if(playing){
            clip.stop();
        }
    }
    public void close(){
        if(clip!=null) clip.close();

        if(stream!=null){
            try{
                stream.close();
            }catch (IOException exception){
                exception.printStackTrace();
            }
        }
    }

    public static Sound playSound(String path){
        File file = new File(path);
        if(playing){
            stop();
        }
        Sound sound = new Sound(file);
        sound.play();
        return sound;
    }
/*
    public void setVolume(float x) {
        if (x<0) x = 0;
        if (x>1) x = 1;
        float min = volumeControl.getMinimum();
        float max = volumeControl.getMaximum();
        volumeControl.setValue((max-min)*x+min);
    }

    public float getVolume() {
        float v = volumeControl.getValue();
        float min = volumeControl.getMinimum();
        float max = volumeControl.getMaximum();
        return (v-min)/(max-min);
    }
*/
}
