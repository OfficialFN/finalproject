import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;
public class Game {
    int score = 0;
    JLabel s;
    public Game() {

        JFrame frame = new JFrame();
        GamePanel gPanel = new GamePanel();
        JPanel b = new JPanel(new FlowLayout(FlowLayout.LEFT));
        s = new JLabel(""+score);
        b.add(s);
        gPanel.add(b, BorderLayout.NORTH);


        frame.add(gPanel);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500,500);
        frame.setVisible(true);
    }
    class GamePanel extends JPanel implements MouseListener, MouseMotionListener{
        boolean[][] sand = new boolean[500][500];
        public GamePanel() {
            setBackground(Color.WHITE);
            addMouseListener(this);
            addMouseMotionListener(this);
            for (int i = 0; i < sand.length; i++) {
                for (int j = 0; j < 150; j++) {
                    sand[i][j] = true;
                }
            }
        }

        // This is the method that draws everything
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            for (int i = 0; i < sand.length; i++) {
                for (int j = 0; j < sand[0].length; j++) {
                    if (!sand[i][j]) {
                        g.setColor(Color.YELLOW);
                        g.drawRect(i, j, 1, 1);
                    } else {
                        g.setColor(Color.WHITE);
                        g.drawRect(i, j, 1, 1);
                    }
                }
            }

        }

        public void mousePressed(MouseEvent e) {
            System.out.println("a");

            for (int i = -12; i <= 12; i++) {
                for (int j = -12; j <= 12; j++) {
                    if (i * i + j * j <= 144) {
                        int x = e.getX() + i;
                        int y = e.getY() + j;
                        if (x >= 0 && x < sand.length && y >= 0 && y < sand[0].length) {
                            sand[x][y] = true;
                            score++;
                            s.setText(""+score);
                        }
                    }
                }
            }
            repaint();
        }

        public void mouseReleased(MouseEvent e) {

        }

        public void mouseEntered(MouseEvent e) {

        }

        public void mouseExited(MouseEvent e) {

        }

        public void mouseDragged(MouseEvent e) {
            mousePressed(e);
        }

        public void mouseMoved(MouseEvent e) {

        }

        public void mouseClicked(MouseEvent e) {

        }

    }
}
