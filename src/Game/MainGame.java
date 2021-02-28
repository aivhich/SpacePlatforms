package Game;

import Game.Mars.Mountain;
import Game.car.Aircar;
import Game.station.ModuleOxg;
import Game.station.Station;
import Game.station.things.Computer;
//import javazoom.jl.decoder.JavaLayerException;
//import javazoom.jl.player.AudioDevice;
//import javazoom.jl.player.JavaSoundAudioDevice;
//import javazoom.jl.player.advanced.AdvancedPlayer;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;


public class MainGame {
    public static JFrame frame;
    public static JPanel panel;
    //images
    Image fon = new ImageIcon("image/Mars/sky.png").getImage();
    Image fon1 = new ImageIcon("image/Mars/marsSurface.png").getImage();

    public static Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
    public static int groundY;

    //music and sound effects
//  public static AdvancedPlayer ap;
    public static String musicpath = "";
    //public static Music music = new Music();
    public static Sound sound = new Sound();

    volatile public static JLabel thrustL, messageL, tasksL;
    //objects
    public static Save Savedata = new Save();
    public static Station station;
    public static Mountain mountain;
    public static ModuleOxg moduleOxg;
    public static Npc[] npc = new Npc[3];
    public static Greenhouse greenhouse;
    public static Aircar aircar;
    public static Computer computer;
    public static Alien alien;
    private Font Msg = new Font("TimesRoman", Font.BOLD, 30);
    private Font Finterface = new Font("TimesRoman", Font.BOLD, 18);


    //hero and objects
    public static Pers pers;
    static Rover rover;

    public MainGame() {
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(size.width, size.height-20);
        frame.setLocation(0,0);
        groundY = frame.getHeight()-111;

        panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                drowing(g);
            }
        };
        panel.setLayout(null);

        ///надписи
        messageL = new JLabel("");
        thrustL = new JLabel("Тяга:");
        tasksL = new JLabel("Задания: нет");

        thrustL.setBounds(20,1,200,50);
        tasksL.setBounds(20, 50, 450, 200);
        messageL.setBounds(frame.getWidth()/2-200,80,800,50);
        messageL.setFont(Msg);
        tasksL.setFont(Finterface);
        thrustL.setFont(Finterface);
        thrustL.setForeground(Color.white);
        tasksL.setForeground(Color.white);
        messageL.setForeground(Color.ORANGE);
        panel.add(thrustL);
        panel.add(messageL);
        panel.add(tasksL);

        ///create objects

        for(int i = 0; i<1; i++) npc[i] = new Npc(" Mark","Captain",1, 300, 100);

        pers = new Pers();

        npc[0].run();
        station = new Station(150, groundY-179);
        moduleOxg = new ModuleOxg(station.getX()+station.getImg().getWidth(null), groundY-87);
        mountain = new Mountain(10, groundY-478);
        computer = new Computer(400, 0);
        rover = new Rover();
        alien = new Alien(5100, 100);

        aircar = new Aircar(1280, groundY);
        greenhouse = new Greenhouse(2300, groundY-113);
        frame.add(panel);
        frame.setVisible(true);
        panel.repaint();
    }




    void drowing(Graphics g){
        //fons
        g.drawImage(fon, 0,0, frame.getWidth(), frame.getHeight(), null);
        g.drawImage(fon1, 0,groundY, frame.getWidth(), fon1.getHeight(null), null);
        g.drawImage(mountain.getImg(), mountain.getX(), mountain.getY(),null);

        //objects
        g.drawImage(ModuleOxg.getImg(), ModuleOxg.getX(),ModuleOxg.getY(),null);
        g.drawImage(station.getImg(), station.getX(),station.getY(),null);
        g.drawImage(greenhouse.getImage(), greenhouse.getX(), greenhouse.getY(),null);
        g.drawImage(rover.getImage(), rover.getX(), rover.getY(),null);
        g.drawImage(alien.getImage(), alien.getX(), alien.getY(), null);

        //things
        g.drawImage(computer.getImage(), computer.getX(), computer.getY(),null);
        for(int i=0; i<2; i++){
            g.drawImage(station.gateway[i].getImage(), station.gateway[i].getX(), station.gateway[i].getY(),null);
            g.drawImage(greenhouse.gateway[i].getImage(), greenhouse.gateway[i].getX(), greenhouse.gateway[i].getY(),null);
        }
        g.drawImage(aircar.getImg(), aircar.getX(), aircar.getY(), null);
        for(int i =0; i<1; i++){
            g.drawImage(npc[i].getImage(), npc[i].getX(), npc[i].getY(), null);
            if(npc[i].getDialog().isVisible()) {
                g.drawImage(npc[i].getDialog().getImage(), npc[i].getDialog().getX(), npc[i].getDialog().getY(), null);
            }
        }
        for(int i=0; i<3; i++){
            g.drawImage(aircar.engeens[i].getImg(), aircar.engeens[i].getX(), aircar.engeens[i].getY(), null);
        }
        g.drawImage(alien.blaster.getImage(), alien.blaster.getX(), alien.blaster.getY(), null);
        g.drawImage(alien.blaster.getShot().getImage(), alien.blaster.getShot().getX(), alien.blaster.getShot().getY(), null);
        g.drawImage(pers.getImage(), pers.getX(), pers.getY(), null);

    }


//    static class Music extends Thread {
//        @Override
//        public void run() {
//            try {
//                InputStream is = new FileInputStream(musicpath);
//                AudioDevice device = new JavaSoundAudioDevice();
//                ap = new AdvancedPlayer(is, device);
//                ap.play();
//                ap.stop();
//                ap.close();
//            } catch (FileNotFoundException | JavaLayerException e) {
//                e.printStackTrace();
//            }
//        }
//    }
}

