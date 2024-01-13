import javax.swing.*;

public class Game extends JFrame {

    public Game() {
        this.add(new Graphics());
        this.setTitle("GIGA CHADS Snake Game");
        this.pack();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(true);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }

}
