package GamePlatformer;

import Game.MainGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainGamePlatform {
    public static JFrame frame;
    public static JPanel panel;
    static int groundY;
    static Plato[] plato = new Plato[20];

    public static GamePlatformer.Pers pers;
    static Ground []ground = new Ground[3];
    Image fon = new ImageIcon("image/alienStation/fon.png").getImage();
    Image fontask = new ImageIcon("image/null.png").getImage();
    Timer taskT;
    static Save Savedata;

    static JLabel coinL = new JLabel("Coin: 0");
    public static JLabel liveL = new JLabel("Live: 10");

    private Font coinF = new Font("TimesRoman", Font.BOLD,18);


    public static Coin[] coin = new Coin[15];

    static int[] plX = new int[20];
    static int[] plY = new int[20];


    public static AlienP alienP[] = new AlienP[5];


    public static Dimension size = Toolkit.getDefaultToolkit().getScreenSize();

    public MainGamePlatform() {
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(size.width,size.height-20);
        groundY = MainGamePlatform.frame.getHeight()-111;
        frame.setLocation(0,0);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        Savedata = new Save();


        pers = new Pers();
        plato[0] = new Plato();
        
        plX[0]=MainGamePlatform.frame.getWidth()-900;
        plY[0]=MainGamePlatform.groundY-150;
        plato[0].setX(plX[0]);
        plato[0].setY(plY[0]);
        for(int i=1; i<20; i++) {
            plato[i] = new Plato();
            plX[i]=plX[i-1]+plato[0].getImage().getWidth(null)+(50+(int) (Math.random() * 150));
            if(plY[i-1]<100&&(plY[i-1]<(MainGamePlatform.groundY-450))) {
                plY[i] = plY[i - 1]+(-40) + (int) (Math.random() * (100));
            }else if(plY[i-1]>(MainGamePlatform.groundY-400)){
                plY[i] = plY[i - 1]+((-100) + (int) (Math.random() * (- 50)));
            }else{
                plY[i] = plY[i - 1]-((-50) + (int) (Math.random() * (100)));
            }
            plato[i].setY(plY[i]);
            plato[i].setX(plX[i]);
        }



        for(int i = 0; i<3; i++) {
            ground[i]= new Ground();
            ground[i].setY(groundY);
            ground[i].setX(-(frame.getWidth())+(ground[i].getImage().getWidth(null)*i));
        }

        pers.setY(groundY-pers.getImage().getHeight(null));
        pers.setX(100);

        for(int i = 0; i<5; i++){
            alienP[i] = new AlienP(0+ (int) (Math.random() * (MainGamePlatform.frame.getWidth()*5)), groundY);
            alienP[i].setVisible(true);
            alienP[i].run();
        }
        for(int i=0; i<10; i++){
            coin[i]=new Coin();
            coin[i].setX(plato[i].getX()+(100+ (int) (Math.random() * 500)));
            coin[i].setY(plato[i].getY()-coin[i].getImage().getWidth(null)-20);
        }
        for(int i=10; i<15; i++){
            coin[i]=new Coin();
            coin[i].setX(0+ (int) (Math.random() * (MainGamePlatform.frame.getWidth()*5)));
            coin[i].setY(groundY-coin[i].getImage().getHeight(null)-20);
        }


        NewTask();
        panel = new JPanel() {
            @Override
            protected void paintComponent (Graphics g){
                super.paintComponent(g);
                g.drawImage(fon, 0, 0, frame.getWidth(), frame.getHeight(), null);
                for(int i=0; i<3; i++){
                    g.drawImage(ground[i].getImage(), ground[i].getX(), ground[i].getY(), null);
                }
                for(int i=0; i<20; i++) {
                    g.drawImage(plato[i].getImage(), plato[i].getX(), plato[i].getY(), null);
                }

                for(int i=0; i<15; i++){
                    g.drawImage(coin[i].getImage(), coin[i].getX(), coin[i].getY(), null);
                }

                for(int i = 0; i<5; i++) {
                    g.drawImage(alienP[i].getImage(), alienP[i].getX(), alienP[i].getY(), null);
                    if(alienP[i].getBlaster().isVisible())g.drawImage(alienP[i].getBlaster().getImage(), alienP[i].getBlaster().getX(), alienP[i].getBlaster().getY(), null);
                    if(alienP[i].getBlaster().getShot().isVisible())g.drawImage(alienP[i].getBlaster().getShot().getImage(), alienP[i].getBlaster().getShot().getX(), alienP[i].getBlaster().getShot().getY(), null);
                }

                g.drawImage(pers.getImage(), pers.getX(), pers.getY(),null);

                g.drawImage(fontask, 0, 0, frame.getWidth(), frame.getHeight(), null);
            }
        };

        panel.setLayout(null);
        frame.add(panel);

        coinL.setBounds(20 , 20, 200, 100);
        liveL.setBounds(20, 60, 200, 100);
        coinL.setFont(coinF);
        liveL.setFont(coinF);
        panel.add(liveL);
        panel.add(coinL);
        coinL.setVisible(true);
        liveL.setVisible(true);

        //MainGamePlatform.pers.saves(true);
        frame.setVisible(true);
        panel.repaint();
    }

    void NewTask(){
        taskT = new Timer(2000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fontask = new ImageIcon("image/fonTask.png").getImage();
                taskT.stop();
                fontask = new ImageIcon("image/null.png").getImage();
                panel.repaint();
            }
        });
        taskT.start();
    }
}
