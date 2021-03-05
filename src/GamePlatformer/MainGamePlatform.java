package GamePlatformer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainGamePlatform {
    public static JFrame frame;
    public static JPanel panel;
    int groundY;
    static Plato[] plato = new Plato[20];

    static GamePlatformer.Pers pers;
    Ground []ground = new Ground[3];
    Image fon = new ImageIcon("image/alienStation/fon.png").getImage();
    Image fontask = new ImageIcon("image/fonTask.png").getImage();
    Timer taskT;

    static int[] plX = {-5000,-4700,-4500,-4200,-4000,-3800,-3600,-3400, -3200, -3500, -3000, -2800, -2300, -2000, -1800, -1500, -1300, -1100, -800, -500};
    static int[] plY = {100,200,300,200,300,400,500,400,200,100,200,300,400,500,600,500,300,400,500,600};


    public static Dimension size = Toolkit.getDefaultToolkit().getScreenSize();

    public MainGamePlatform() {
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(size.width,size.height-20);
        frame.setLocation(0,0);

        groundY =frame.getHeight()-111;
        pers = new Pers();
        for(int i=0; i<20; i++) {
            plato[i] = new Plato();
            plato[i].setX(plX[i]);
            plato[i].setY(plY[i]);
        }
        for(int i = 0; i<3; i++) {
            ground[i]= new Ground();
            ground[i].setY(groundY);
            ground[i].setX(-(frame.getWidth())+(ground[i].getImage().getWidth(null)*i));
        }
        //pers.setImage(new ImageIcon("image/Pers/died.png").getImage());
        pers.setY(groundY-pers.getImage().getHeight(null));
        pers.setX(frame.getWidth()-200);
        NewTask();
        panel = new JPanel() {
            @Override
            protected void paintComponent (Graphics g){
                super.paintComponent(g);
                g.drawImage(fon, 0, 0, frame.getWidth(), frame.getHeight(), null);
                for(int i=0; i<3; i++){
                    g.drawImage(ground[i].getImage(), ground[i].getX(), ground[i].getY(), null);
                }
                g.drawImage(pers.getImage(), pers.getX(), pers.getY(),null);
                for(int i=0; i<20; i++) {
                    g.drawImage(plato[i].getImage(), plato[i].getX(), plato[i].getY(), null);
                }
                g.drawImage(fontask, 0, 0, frame.getWidth(), frame.getHeight(), null);
            }
        };
        panel.setLayout(null);
        frame.add(panel);
        frame.setVisible(true);
        panel.repaint();
    }

    void NewTask(){
        taskT = new Timer(5000, new ActionListener() {
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
