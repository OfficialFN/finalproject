import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Game extends JPanel implements ActionListener{
    double gamescore;
    int score = 0;
    int fps;
    JLabel s;
    JLabel fpslabel;
    GamePanel gPanel;
    boolean isHolding = false;
    int lastlocx;
    int lastlocy;
    JButton pour;
    boolean canerase = true;
    boolean pouring = false;
    int level;
    JPanel res;
    boolean changing;
    boolean running = true;
    public Game(int level) {
        this.setLayout(new BorderLayout());
        this.level= level;
        gPanel = new GamePanel();
        JPanel b = new JPanel(new FlowLayout(FlowLayout.LEFT));
        s = new JLabel("Score: "+score);
        fpslabel = new JLabel("FPS: " + fps);
        pour = new JButton("Pour");
        pour.addActionListener(this);
        b.add(s);
        b.add(fpslabel);
        b.add(pour);
        gPanel.add(b, BorderLayout.NORTH);


        this.add(gPanel);
        this.setSize(500,500);
        this.setVisible(true);
        Thread loopThread = new Thread(this::gameLoop);
        loopThread.start();
    }
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Pour")) {
            canerase = false;
            pouring = true;
        }
    }

    public void gameLoop(){
        final int targetfps = 60;
        final long nsbetween = 1000000000/ targetfps;
        long looptime = System.nanoTime();
        long lastFpsTime = 0;
        while (running) {
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
            if (pouring) {
                gPanel.pour();
            }
            if (!changing && pouring) {
                results();
                running = false;
            }
            if (running){
                gPanel.render();


                gPanel.clear();

                try {
                    Thread.sleep((looptime - System.nanoTime() + nsbetween) / 1000000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public void results() {
        try {
            res = new JPanel(new FlowLayout());
            res.add(new JLabel("Score: " + gamescore));

            int targetcount = gPanel.check();
            System.out.println(targetcount);
            JLabel goals = new JLabel();
            switch (targetcount) {
                case 0 -> goals.setText("Extra Targets: ❌❌❌");
                case 1 -> goals.setText("Extra Targets: ✅❌❌");
                case 2 -> goals.setText("Extra Targets: ✅✅❌");
                case 3 -> goals.setText("Extra Targets: ✅✅✅");
                default -> goals.setText("error");
            }
            res.add(goals);
            JLabel t = new JLabel();
            if (gPanel.checkgoal()) {
                t.setText("Main Target: ✅");
            } else {
                t.setText("Main Target: ❌");
            }
            res.add(t);
            this.remove(gPanel);
            this.add(res,BorderLayout.CENTER);
            this.revalidate();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    class GamePanel extends JPanel implements MouseListener, MouseMotionListener{
        boolean[][] sand = new boolean[500][500];
        boolean[][] water = new boolean[500][500];
        boolean[][] goal = new boolean[500][500];

        boolean[][] stone = new boolean[500][500];
        public GamePanel() {

            setPreferredSize(new Dimension(500, 500));
            setBackground(Color.WHITE);
            addMouseListener(this);
            addMouseMotionListener(this);
            for (int i = 0; i < sand.length; i++) {
                for (int j = 0; j < sand[0].length; j++) {
                    if (j > 100) {
                        sand[i][j] = true;
                    } else {
                        water[i][j] = true;
                    }

                }

            }
            if(level==3) {
                for (int i = 0; i < 230; i++) {
                    for (int j = 280; j < 300; j++) {
                        stone[i][j] = true;
                    }
                }

                for (int i = 250; i < 500; i++) {
                    for (int j = 280; j < 300; j++) {
                        stone[i][j] = true;
                    }
                }

                for (int i = 380; i < 410; i++) {
                    for (int j = 180; j < 280; j++) {
                        stone[i][j] = true;
                    }
                }

                for (int i = 30; i < 50; i++) {
                    for (int j = 250; j < 270; j++) {
                        goal[i][j] = true;
                    }
                }

                for (int i = 450; i < 470; i++) {
                    for (int j = 250; j < 270; j++) {
                        goal[i][j] = true;
                    }
                }

                for (int i = 440; i < 460; i++) {
                    for (int j = 440; j < 460; j++) {
                        goal[i][j] = true;
                    }
                }

                for (int i = 50; i < 150; i++) {
                    for (int j = 400; j < 480; j++) {
                        goal[i][j] = true;
                    }
                }
            }
        }
        public int check() {
            int targetcount = 0;
            outer:
            for (int i = 30; i < 50; i++) {
                for (int j = 250; j < 270; j++) {
                    if (water[i][j]) {
                        targetcount++;
                        break outer;
                    }
                }
            }
            outer2:
            for (int i = 450; i < 470; i++) {
                for (int j = 250; j < 270; j++) {
                    if (water[i][j]) {
                        targetcount++;
                        break outer2;
                    }
                }
            }
            outer3:
            for (int i = 440; i < 460; i++) {
                for (int j = 440; j < 460; j++) {
                    if (water[i][j]) {
                        targetcount++;
                        break outer3;
                    }
                }
            }
            return targetcount;
        }
        public boolean checkgoal() {
            for (int i = 50; i < 150; i++) {
                for (int j = 400; j < 480; j++) {
                    if (water[i][j]) {
                        return true;
                    }
                }
            }
            return false;
        }


        public void render() {
            repaint();
        }
        boolean[][] tempwater = new boolean[water.length][water[0].length];
        public void clear() {
            for (int i = 0; i < water.length; i++) {
                for (int j = 0; j < water[0].length; j++) {
                    tempwater[i][j] = false;
                }
            }
        }

        public void pour() {
            changing = false;
            for (int i = 0; i < water.length; i++) {
                for (int j = 0; j < water[i].length; j++) {
                    tempwater[i][j] = water[i][j];
                }
            }

            for (int i = 0; i < sand.length; i++) {
                for (int j = 0; j < sand[i].length; j++) {
                    if (!sand[i][j] && !tempwater[i][j] && !stone[i][j]) {
                        boolean nexttowater = (i > 0 && tempwater[i - 1][j]) || (i < sand.length - 1 && tempwater[i + 1][j]) || (j > 0 && tempwater[i][j - 1]) || (j < sand[i].length - 1 && tempwater[i][j + 1]);

                        if (nexttowater) {
                            changing = true;
                            water[i][j] = true;
                        }
                    }
                }
            }
        }
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            for (int i = 0; i < sand.length; i++) {
                for (int j = 0; j < sand[0].length; j++) {
                    if (goal[i][j]) {
                        g.setColor(Color.GREEN);
                        g.drawRect(i,j,1,1);
                    } else if (stone[i][j]) {
                        g.setColor(Color.GRAY);
                        g.drawRect(i,j,1,1);
                    } else if (sand[i][j]) {
                        g.setColor(Color.YELLOW);
                        g.drawRect(i, j, 1, 1);
                    } else if (water[i][j]) {
                        g.setColor(Color.BLUE);
                        g.drawRect(i,j,1,1);
                    } else {
                        g.setColor(Color.WHITE);
                        g.drawRect(i, j, 1, 1);
                    }
                }
            }
            gamescore = 100 - score/2000.0;
            s.setText("Score: "+gamescore);

        }

        public void mousePressed(MouseEvent e) {
            if (canerase){
                isHolding = true;
                lastlocx = e.getX();
                lastlocy = e.getY();

                for (int i = -12; i <= 12; i++) {
                    for (int j = -12; j <= 12; j++) {
                        if (i * i + j * j <= 144) {
                            int x = e.getX() + i;
                            int y = e.getY() + j;
                            if (x >= 0 && x < sand.length && y >= 0 && y < sand[0].length) {
                                if (sand[x][y]) {
                                    sand[x][y] = false;
                                    score++;
                                }

                            }
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
            if (canerase){
                int x = e.getX();
                int y = e.getY();
                int step = 15;
                int stepX = (x - lastlocx) / step;
                int stepY = (y - lastlocy) / step;
                for (int a = 0; a < step; a++) {
                    int newX = lastlocx + stepX * a;
                    int newY = lastlocy + stepY * a;
                    connect(newX, newY);
                }
                mousePressed(e);
            }
        }

        public void mouseMoved(MouseEvent e) {

        }

        public void mouseClicked(MouseEvent e) {

        }

        public void connect(int x, int y) {
            for (int i = -12; i <= 12; i++) {
                for (int j = -12; j <= 12; j++) {
                    if (i * i + j * j <= 144) {
                        if (x+i >= 0 && x+i < sand.length && y+j >= 0 && y+j < sand[0].length) {
                            if (sand[x+i][y+j]) {
                                sand[x+i][y+j] = false;
                                score++;
                            }

                        }

                    }
                }
            }
        }
    }
}
