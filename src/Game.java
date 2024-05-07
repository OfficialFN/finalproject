import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class Game {
    public Game() {
        JFrame frame = new JFrame();

        GamePanel gPanel = new GamePanel();
        frame.add(gPanel);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500,500);
        frame.setVisible(true);
    }
    class GamePanel extends JPanel implements MouseListener, MouseMotionListener{
        boolean[][] sand = new boolean[500][500];
        public GamePanel() {
            setBackground(Color.WHITE);
        }

        // This is the method that draws everything
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            for (int i = 0; i < sand.length; i++) {
                for (int j = 0; j < sand[0].length; j++) {
                    if (sand[i][j]) {
                        g.setColor(Color.YELLOW);
                        g.drawRect(i, j, 1, 1);
                    }
                }
            }

        }

        public void mousePressed(MouseEvent e) {
            System.out.println("a");
            for (int i = -12; i < 12; i++) {
                for (int j = -12; j < 12; j++) {
                    sand[e.getX() + i][e.getY() + j] = true;
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
