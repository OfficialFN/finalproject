import javax.swing.*;
public class Main {
    static int fps = 0;
    //Game game = new Game();
    public static void gameLoop(){
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
                System.out.println("(FPS: "+fps+")");
                lastFpsTime = 0;
                fps = 0;
            }



            try {
                Thread.sleep( (looptime-System.nanoTime() + nsbetween)/1000000 );
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
    }
    public static void main(String[] args) {
        Main.gameLoop();
    }
}