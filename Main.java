import java.awt.*;
import javax.swing.*;

public class Main extends JFrame{

    public static void main (String[] args) {
        JFrame frame = new JFrame();
        frame.setTitle("Escape Nightmare");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1100, 1100);

        GameGUI gui = new GameGUI();
        frame.add(gui);
        frame.addKeyListener(gui);

        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

}
