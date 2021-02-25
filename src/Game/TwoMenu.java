package Game;

import javax.crypto.IllegalBlockSizeException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class TwoMenu {
    JFrame frame = new JFrame();
    JPanel panel;
    JButton continueB = new JButton("Продолжить");
    JButton cancelB = new JButton("Сохранить и выйти");

    Image image = new ImageIcon("image/menu.png").getImage();

    public static Dimension size = Toolkit.getDefaultToolkit().getScreenSize();

    public TwoMenu(){
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(300, 200);
        frame.setLocation(size.width/2-frame.getWidth()/2,size.height/2-frame.getHeight()/2);
        panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(image, 0,0, frame.getWidth(), frame.getHeight(), null);
            }
        };
        panel.setLayout(null);
        frame.setUndecorated(true);
        continueB.setBounds(frame.getWidth()/2-100, 50, 200, 40);
        cancelB.setBounds(frame.getWidth()/2-100, 100, 200, 40);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        panel.add(continueB);
        panel.add(cancelB);
        frame.add(panel);
        frame.setVisible(true);
        ActionBut();
    }
    void ActionBut(){
        continueB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    MainGame.Savedata.read();
                } catch (IOException exception) {
                    exception.printStackTrace();
                }
                frame.dispose();
            }
        });
        cancelB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainGame.pers.saves(false);
                MainGame.Savedata.save();
                System.exit(0);
            }
        });
    }
}
