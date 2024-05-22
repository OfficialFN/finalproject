import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class Game extends JPanel {
    int score = 0;
    int fps;
    JLabel s;
    JLabel fpslabel;
    GamePanel gPanel;
    boolean isHolding = false;
    int lastlocx;
    int lastlocy;


    public Game() {

        gPanel = new GamePanel();
        JPanel b = new JPanel(new FlowLayout(FlowLayout.LEFT));
        s = new JLabel("Score: "+score);
        fpslabel = new JLabel("FPS: " + fps);
        b.add(s);
        b.add(fpslabel);
        gPanel.add(b, BorderLayout.NORTH);

        this.add(gPanel);
        this.setSize(500,500);
        this.setVisible(true);
        Thread loopThread = new Thread(this::gameLoop);
        loopThread.start();
    }

    public void gameLoop(){
        final int targetfps = 60;
        final long nsbetween = 1000000000/ targetfps;
        long looptime = System.nanoTime();
        long lastFpsTime = 0;
        while (true) {
            long now = System.nanoTime();
            long timedif = now - looptime;
            looptime = now;
            lastFpsTime += timedif;
            fps++;
            if (lastFpsTime >= 1000000000)
            {
                fpslabel.setText("FPS "+fps);
                lastFpsTime = 0;
                fps = 0;
            }

            gPanel.render();

            try {
                Thread.sleep( (looptime-System.nanoTime() + nsbetween)/1000000 );
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
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


        public void render() {
            repaint();
            //System.out.println("repaint");
        }
        protected void paintComponent(Graphics g) {
            System.out.println("a");
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
            score = 0;
            for (int i = 0; i < sand.length; i++) {
                for (int j = 150; j < sand[0].length; j++) {
                    if (sand[i][j]) {
                        score++;
                        s.setText("Score: "+score);

                    }
                }
            }

        }

        public void mousePressed(MouseEvent e) {
            isHolding = true;
            lastlocx = e.getX();
            lastlocy = e.getY();

            for (int i = -12; i <= 12; i++) {
                for (int j = -12; j <= 12; j++) {
                    if (i * i + j * j <= 144) {
                        int x = e.getX() + i;
                        int y = e.getY() + j;
                        if (x >= 0 && x < sand.length && y >= 0 && y < sand[0].length) {
                            sand[x][y] = true;
                        }
                    }
                }
            }
        }

        public void mouseReleased(MouseEvent e) {

        }

        public void mouseEntered(MouseEvent e) {

        }

        public void mouseExited(MouseEvent e) {

        }

        public void mouseDragged(MouseEvent e) {
            int x = e.getX();
            int y = e.getY();
            int step = 15;
            int stepX = (x - lastlocx) / step;
            int stepY = (y - lastlocy) / step;
            for (int a = 0; a < step; a++) {
                int newX = lastlocx + stepX * a;
                int newY = lastlocy + stepY * a;
                System.out.println(newX + " " + newY);
                connect(newX,newY);
            }
            mousePressed(e);
        }

        public void mouseMoved(MouseEvent e) {

        }

        public void mouseClicked(MouseEvent e) {

        }

        public void connect(int x, int y) {
            for (int i = -12; i <= 12; i++) {
                for (int j = -12; j <= 12; j++) {
                    if (i * i + j * j <= 144) {
                        if (x >= 0 && x < sand.length && y >= 0 && y < sand[0].length) {
                            sand[x+i][y+j] = true;
                        }

                    }
                }
            }
        }
    }
}
