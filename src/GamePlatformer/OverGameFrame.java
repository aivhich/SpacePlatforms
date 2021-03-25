package GamePlatformer;

import javax.swing.*;
import java.awt.*;

public class OverGameFrame {
    public JFrame frame = new JFrame();
    public JPanel panel;
    Image fon  = new ImageIcon("image/gameover.png").getImage();

    public static Dimension size = Toolkit.getDefaultToolkit().getScreenSize();

    public OverGameFrame() {
        frame.setSize(400, 300);
        frame.setLocation(size.width/2-frame.getWidth()/2, size.height/2-frame.getHeight()/2);
        panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g){
                super.paintComponent(g);
                g.drawImage(fon ,0, 0, frame.getWidth()-14, frame.getHeight()-35, null);
            }
        };
        panel.repaint();
        frame.add(panel);
        frame.setVisible(true);
    }
}
