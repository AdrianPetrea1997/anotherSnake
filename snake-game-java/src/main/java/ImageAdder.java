
import javax.swing.*;
public class ImageAdder {
    public static void main(String[] args) {
        JFrame frame =new JFrame();
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setUndecorated(true);
        frame.setSize(800,800);
        frame.setVisible(true);

        frame.setResizable(false);
        frame.setLocationRelativeTo(null);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel label = new JLabel();
        label.setIcon(new ImageIcon("C:\\Users\\Acasa\\Desktop\\java pro\\Frame\\Picture\\PictureDesert.jpeg"));

        frame.add(label);
        frame.setVisible(true);

    }
}
