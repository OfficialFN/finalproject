import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class Home implements ActionListener{
    JFrame frame;
    JPanel home;
    JButton playbutton;
    JButton howto;
    public Home () {
        frame = new JFrame();
        home = new JPanel();
        home.setLayout(new FlowLayout());
        playbutton = new JButton("Play");
        playbutton.setPreferredSize(new Dimension(100,50));
        playbutton.addActionListener(this);
        home.add(playbutton);

        howto = new JButton("How to play");
        howto.setPreferredSize(new Dimension(100,50));
        home.add(howto);

        frame.add(home);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500,500);
        frame.setVisible(true);


    }
    public void actionPerformed(ActionEvent e) {
        frame.remove(home);
        frame.add(new Game());
    }
}
