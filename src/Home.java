import javax.swing.*;
import java.awt.*;

public class Home {
    JFrame home;
    JButton playbutton;
    JButton howto;
    public Home () {
        home = new JFrame();
        home.setLayout(new FlowLayout());
        playbutton = new JButton("Play");
        playbutton.setPreferredSize(new Dimension(100,50));
        home.add(playbutton);

        howto = new JButton("How to play");
        howto.setPreferredSize(new Dimension(100,50));
        home.add(howto);
        home.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        home.setSize(500,500);
        home.setVisible(true);

    }
}
