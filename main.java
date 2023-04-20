import java.awt.Canvas;
import java.awt.Graphics;
import javax.swing.JFrame;
import java.util.Random;

class Drawing extends Canvas {

    static int canvasSize = 1000;
    static int boxSize = 200;

    public static void main(String[] args) {
        JFrame frame = new JFrame("My Drawing");
        Canvas canvas = new Drawing();
        canvas.setSize(canvasSize, canvasSize);
        frame.add(canvas);
        frame.pack();
        frame.setVisible(true);
    }

 

    public void paint(Graphics g) {
        Random random = new Random();

        int numberOfBoxes = canvasSize / boxSize;
        boolean[][] map = new boolean[numberOfBoxes][numberOfBoxes / 2];        

        for (int x = 0; x < canvasSize / 2; x++) {
            for (int y = 0; y < canvasSize; y++) {
                // map[x][y] = false;
                if (random.nextInt(2) == 1) {
                    // map[x][y] = true;
                    g.fillRect(x*boxSize, y*boxSize, boxSize, boxSize);
                    g.fillRect((numberOfBoxes - x)*boxSize, y*boxSize, boxSize, boxSize);
                }
            }
        }
    }
}