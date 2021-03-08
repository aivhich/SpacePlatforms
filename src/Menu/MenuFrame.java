package Menu;

import Game.MainGame;
import Menu.planets.Earth;
import Menu.planets.Mars;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static Game.MainGame.aircar;
import static Game.MainGame.panel;

public class MenuFrame{
    JFrame frame = new JFrame();
    JPanel panel;
    JButton startB = new JButton("Начать игру");
    JButton cancelB = new JButton("Выйти");
    JButton about = new JButton("Об игре");

    Timer aboutT;

    public static Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
    int Xsun = size.width/2-100, Ysun=size.height/2-100;
    Image fon = new ImageIcon("image/spacemap2.png").getImage();
    Image sun= new ImageIcon("image/Planet/0.png").getImage();
    Image mars= new ImageIcon("image/Planet/5.png").getImage();
    Image earth= new ImageIcon("image/Planet/3.png").getImage();
    Image mercu= new ImageIcon("image/Planet/1.png").getImage();
    Image moon= new ImageIcon("image/Planet/4.png").getImage();
    Image saturn= new ImageIcon("image/Planet/7.png").getImage();
    Image neptune= new ImageIcon("image/Planet/6.png").getImage();
    Image fonabout;

    public MenuFrame(){
        frame.setSize(size.width, size.height-20);
        frame.setLocation(0,0);

        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(fon, 0, 0,frame.getWidth(),frame.getHeight(),null);
                g.drawImage(sun, Xsun, Ysun,null);
                g.drawImage(mercu, frame.getWidth()/10*4+50, Ysun+30,null);
                g.drawImage(mars, frame.getWidth()/10*1, Ysun+20,null);
                g.drawImage(earth, frame.getWidth()/10*3, Ysun+20,null);
                g.drawImage(moon, frame.getWidth()/10*3+100, Ysun+30,null);
                g.drawImage(saturn, frame.getWidth()/10*5, Ysun+30,null);
                g.drawImage(neptune, frame.getWidth()/10*6, Ysun+10,null);
                g.drawImage(fonabout, 0, 0,frame.getWidth(),frame.getHeight(),null);
            }
        };
        panel.setLayout(null);
        startB.setBounds(size.width/2-125, size.height/2+125, 250, 40);
        cancelB.setBounds(size.width/2-125, size.height/2+190, 250, 40);
        about.setBounds(size.width/2-125, size.height/2+250, 250, 40);
        panel.add(startB);
        panel.add(about);
        panel.add(cancelB);
        frame.add(panel);
        frame.setVisible(true);
        //orbiting();
        //mars.orbiting();
        ActionBut();
    }

    void ActionBut(){
        startB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MainGame();
                frame.dispose();
            }
        });
        about.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (aboutT != null && aboutT.isRunning()) return;
                abouts();
            }
        });
        cancelB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }
    int i=0;
    void abouts(){
    aboutT = new Timer(10000, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(i==0) {
                fonabout = new ImageIcon("image/about.png").getImage();
                panel.repaint();
            }else{
                fonabout = new ImageIcon("image/null.png").getImage();
                panel.repaint();
                aboutT.stop();
            }
            i++;
        }
    });
    aboutT.start();
}
}
