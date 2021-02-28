package Menu;

import Game.MainGame;
import Menu.planets.Earth;
import Menu.planets.Mars;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static Game.MainGame.panel;

public class MenuFrame {
    JFrame frame = new JFrame();
    JPanel panel;
    JButton startB = new JButton("Начать игру");
    JButton cancelB = new JButton("Выйти");

    Timer orbit;

    Mars mars;
    //Earth earth = new Earth();

    public static Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
    int Xsun = size.width/2-100, Ysun=size.height/2-100;
    Image fon = new ImageIcon("image/spacemap2.png").getImage();
    Image sun= new ImageIcon("image/Planet/0.png").getImage();

    public MenuFrame(){
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(size.width, size.height-20);
        frame.setLocation(0,0);
        panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(fon, 0, 0,null);
                g.drawImage(sun, Xsun, Ysun,null);
                //g.drawImage(mars.getImage(), mars.getX(), mars.getY(), null);
            }
        };
        panel.setLayout(null);

        startB.setBounds(size.width/2-125, size.height/2+125, 250, 40);
        cancelB.setBounds(size.width/2-125, size.height/2+190, 250, 40);
        panel.add(startB);
        panel.add(cancelB);
        frame.add(panel);
        frame.setVisible(true);
        //orbiting();
        mars = new Mars();
        //mars.orbiting();
        ActionBut();
    }
    int xf=1, a=2, c=1, k=2;

    public void orbiting(){
        orbit = new Timer(20, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel.repaint();
            }
        });
        orbit.start();
    }

    void ActionBut(){
        startB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MainGame();
                frame.dispose();
            }
        });
        cancelB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }
}
