package GamePlatformer;

import javax.swing.*;
import java.awt.*;

public class MainGamePlatform {
    JFrame frame;
    JPanel panel;
    int groundY;
    Image fon = new ImageIcon("image/alienStation/fon.png").getImage();

    public static Dimension size = Toolkit.getDefaultToolkit().getScreenSize();

    public MainGamePlatform() {
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(size.width,size.height-20);
        frame.setLocation(0,0);
        groundY =frame.getHeight()-111;

        panel = new JPanel() {
            @Override
            protected void paintComponent (Graphics g){
                super.paintComponent(g);
                g.drawImage(fon, 0, 0, frame.getWidth(), frame.getHeight(), null);
            }
        };
        panel.setLayout(null);
        frame.add(panel);
        frame.setVisible(true);
        panel.repaint();
    }
}
