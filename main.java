import java.awt.Canvas;
import java.awt.Graphics;
import javax.swing.JFrame;
import java.util.Random;
import java.util.Scanner;
import java.awt.image.BufferedImage;
import java.awt.Graphics2D;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

class Drawing extends Canvas {

    static int canvasSize = 500;
    static int boxSize = 100;
    static String option;

    public static void main(String[] args) {
        JFrame frame = new JFrame("My Drawing");
        Drawing canvas = new Drawing();
        canvas.setSize(canvasSize, canvasSize);
        frame.add(canvas);
        frame.pack();
        frame.setVisible(true);
        Scanner scannerInput = new Scanner(System.in);
        while (true) {
            option = scannerInput.next();
            if (option.equals("r")) {
                canvas.repaint();
            }
            if (option.equals("s")) {
                String imageName = scannerInput.next();
                canvas.exportImage(imageName);
            }
            System.out.println("While end");
        }
    }

 

    public void paint(Graphics g) {
        Random random = new Random();

        int numberOfBoxes = canvasSize / boxSize;
        System.out.println(numberOfBoxes);
        // boolean[][] map = new boolean[numberOfBoxes][numberOfBoxes / 2];        
        int xMax = (int) Math.ceil((double)numberOfBoxes / 2);
        for (int x = 0; x < xMax; x++) {
            for (int y = 0; y < numberOfBoxes; y++) {
                // map[x][y] = false;
                // System.out.println(String(x) y);
                if (random.nextInt(2) == 1) {
                    // map[x][y] = true;
                    g.fillRect(x*boxSize, y*boxSize, boxSize, boxSize);
                    g.fillRect((numberOfBoxes - x - 1)*boxSize, y*boxSize, boxSize, boxSize);
                }
            }
        }
    }

    public void exportImage(String imageName) {
        BufferedImage image = new  BufferedImage(canvasSize, canvasSize, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics = image.createGraphics();
        paint(graphics);
        graphics.dispose();
        try {
            System.out.println("Exporting image: "+imageName);
            FileOutputStream out = new FileOutputStream(imageName);
            ImageIO.write(image, "png", out);
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }    
    
    }
}