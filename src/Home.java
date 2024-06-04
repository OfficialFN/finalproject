import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class Home implements ActionListener{
    JFrame frame;
    JPanel home;
    JButton playbutton;
    JButton l1,l2,l3;
    int level=0;
    JPanel name, levels, p, h, a,b,c;
    public Home () {
        frame = new JFrame();
        home = new JPanel();
        home.setLayout(new BoxLayout(home, BoxLayout.Y_AXIS));

        name = new JPanel(new FlowLayout());
        name.setSize(new Dimension(1000, 50));
        JLabel n = new JLabel("Where's My Water? (only level 3 works)");
        name.add(n);
        home.add(name);

        levels = new JPanel(new FlowLayout());
        levels.setPreferredSize(new Dimension(1000, 50));
        l1 = new JButton("Level 1");
        l1.setPreferredSize(new Dimension(100,50));
        l1.addActionListener(this);
        levels.add(l1);

        l2 = new JButton("Level 2");
        l2.setPreferredSize(new Dimension(100,50));
        l2.addActionListener(this);
        levels.add(l2);

        l3 = new JButton("Level 3");
        l3.setPreferredSize(new Dimension(100,50));
        l3.addActionListener(this);
        levels.add(l3);
        home.add(levels);

        p = new JPanel(new FlowLayout());
        playbutton = new JButton("Play");
        playbutton.setPreferredSize(new Dimension(100,50));
        playbutton.addActionListener(this);
        p.add(playbutton);
        home.add(p);


        a = new JPanel();
        home.add(a);
        b = new JPanel();
        home.add(b);
        c = new JPanel();
        home.add(c);

        frame.add(home);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500,500);
        frame.setVisible(true);


    }
    public void changeBack(Color d) {
        name.setBackground(d);
        levels.setBackground(d);
        p.setBackground(d);
        a.setBackground(d);
        b.setBackground(d);
        c.setBackground(d);


    }
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Level 1")) {
            changeBack(Color.GREEN);
            level =1;
        } else if (e.getActionCommand().equals("Level 2")) {
            changeBack(Color.YELLOW);
            level=2;
        } else if (e.getActionCommand().equals("Level 3")) {
            changeBack(Color.RED);
            level=3;
        } else if (e.getActionCommand().equals("Play")) {
            if (level !=0) {
                frame.remove(home);
                frame.add(new Game(level));
            }

        }

    }
}
