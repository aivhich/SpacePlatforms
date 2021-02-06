package Game;

import Game.Mars.Mountain;
import Game.car.Aircar;
import Game.station.ModuleOxg;
import Game.station.Station;
import Game.station.detail.Gateway;
//import javazoom.jl.decoder.JavaLayerException;
//import javazoom.jl.player.AudioDevice;
//import javazoom.jl.player.JavaSoundAudioDevice;
//import javazoom.jl.player.advanced.AdvancedPlayer;

import javax.swing.*;
import java.awt.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;


public class MainGame {
    static JFrame frame;
    public static JPanel panel;
    JLabel radiation, oxygen, live, traction;

    //images
    Image fon = new ImageIcon("image/spacemap1.png").getImage();
    Image fon1 = new ImageIcon("image/Mars/marsSurface.png").getImage();

    public static Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
    public static int groundY;

    //music and sound effects
//    public static AdvancedPlayer ap;
    public static String musicpath = "";
    //public static Music music = new Music();
    public static Sound sound = new Sound();

    public static JLabel thrustL, messageL;
    //objects
    public static Station station;
    public static Mountain mountain;
    public static ModuleOxg moduleOxg;
    public static Greenhouse greenhouse;
    public static Aircar aircar;
    private Font Msg = new Font("TimesRoman", Font.BOLD, 35);


    //hero and objects
    Pers pers;
    Player player[] = new Player[1];
    static Rover rover;

    public MainGame() {
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(size.width, size.height-20);
        frame.setLocation(0,0);

        groundY = frame.getHeight()-111;

        for(int i=0; i<1; i++){
            player[i]=new Player(200, groundY-Player.getImg().getHeight(null));
        }

        panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                drowing(g);
            }
        };
        panel.setLayout(null);

        messageL = new JLabel("Waring!");
        thrustL = new JLabel("Thrust:");
        thrustL.setBounds(20,1,200,50);
        messageL.setBounds(frame.getWidth()/2-100,80,300,50);
        messageL.setFont(Msg);
        thrustL.setForeground(Color.white);
        messageL.setForeground(Color.YELLOW);
        panel.add(thrustL);
        panel.add(messageL);
        ///create objects
        station = new Station(150, groundY-179);
        moduleOxg = new ModuleOxg(station.getX()+station.getImg().getWidth(null), groundY-87);
        mountain = new Mountain(10, groundY-478);
        rover = new Rover();
        pers = new Pers();
        aircar = new Aircar(1000, groundY);
        greenhouse = new Greenhouse(2300, groundY-113);



        radiation = new JLabel("Radiation level:");
        radiation.setBounds(10, 10, 200,45);
        radiation.setIcon(new ImageIcon("image/radiation.png"));
        frame.add(panel);
        //panel.add(radiation);
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
        for(int i=0; i<2; i++){
            g.drawImage(station.gateway[i].getImage(), station.gateway[i].getX(), station.gateway[i].getY(),null);
            g.drawImage(greenhouse.gateway[i].getImage(), greenhouse.gateway[i].getX(), greenhouse.gateway[i].getY(),null);
        }
        g.drawImage(aircar.getImg(), aircar.getX(), aircar.getY(), null);
        for(int i=0; i<3; i++){
            g.drawImage(aircar.engeens[i].getImg(), aircar.engeens[i].getX(), aircar.engeens[i].getY(), null);
        }
        g.drawImage(player[0].getImg(), player[0].getX(), player[0].getY(), null);
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

