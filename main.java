
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JFrame;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.awt.image.BufferedImage;
import java.awt.Graphics2D;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.imageio.ImageIO;

import profile_picture.ProfilePicture;

class Drawing extends Canvas {

    static int canvasSize = 700;
    static int boxSize = 100;
    static String option;
    static Color yellow = new Color(252, 247, 200);
    static Color blue = new Color(200, 210, 255);
    static Color red = new Color(255, 200, 200);
    static Color pink = new Color(255, 200, 230);
    static Color purple = new Color(225, 200, 255);
    static Color teal = new Color(200, 255, 235);
    static Color green = new Color(210, 255, 200);
    static Color orange = new Color(255, 225, 200);

    static ArrayList<ProfilePicture> profilePicturesList = new ArrayList<ProfilePicture>();
    static int memoryLength = 15;
    static int currentProfilePictureIndex;

    public static void main(String[] args) {
        for (int i = 0; i < memoryLength + 1; i++) {
            ProfilePicture profilePicture = makeProfilePicture();
            profilePicturesList.add(profilePicture);
        }
        currentProfilePictureIndex = memoryLength;

        JFrame frame = new JFrame("My Drawing");
        frame.setBackground(Color.BLACK);
        Drawing canvas = new Drawing();
        canvas.setSize(canvasSize, canvasSize);
        frame.add(canvas);
        frame.pack();
        frame.setVisible(true);

        frame.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }
    
            @Override
            public void keyPressed(KeyEvent e) {
                System.out.println("Key pressed code=" + e.getKeyCode() + ", char=" + e.getKeyChar());
                if (e.getKeyChar() == 'r') {
                    canvas.addProfilePictureToMemory();
                    canvas.repaint();
                    System.out.println(profilePicturesList.size());
                }
                if (e.getKeyChar() == 's') {
                    canvas.exportImage("save");
                }
                if (e.getKeyCode() == 37) {
                    currentProfilePictureIndex--;
                    if (currentProfilePictureIndex < 0) {
                        currentProfilePictureIndex = 0;
                    }
                    canvas.repaint();
                }
                if (e.getKeyCode() == 39) {
                    currentProfilePictureIndex++;
                    if (currentProfilePictureIndex > memoryLength) {
                        currentProfilePictureIndex = memoryLength;
                    }
                    if (currentProfilePictureIndex > profilePicturesList.size()) {
                        currentProfilePictureIndex = profilePicturesList.size() - 1;
                    }
                    canvas.repaint();
                }
                if (e.getKeyChar() == '1') {
                    profilePicturesList.get(currentProfilePictureIndex).primaryColor = red;
                    canvas.repaint();
                }
                if (e.getKeyChar() == '2') {
                    profilePicturesList.get(currentProfilePictureIndex).primaryColor = orange;
                    canvas.repaint();
                }
                if (e.getKeyChar() == '3') {
                    profilePicturesList.get(currentProfilePictureIndex).primaryColor = yellow;
                    canvas.repaint();
                }
                if (e.getKeyChar() == '4') {
                    profilePicturesList.get(currentProfilePictureIndex).primaryColor = green;
                    canvas.repaint();
                }
                if (e.getKeyChar() == '5') {
                    profilePicturesList.get(currentProfilePictureIndex).primaryColor = blue;
                    canvas.repaint();
                }
                if (e.getKeyChar() == '6') {
                    profilePicturesList.get(currentProfilePictureIndex).primaryColor = purple;
                    canvas.repaint();
                }
                if (e.getKeyChar() == '7') {
                    profilePicturesList.get(currentProfilePictureIndex).primaryColor = Color.WHITE;
                    canvas.repaint();
                }

                if (e.getKeyChar() == '!') {
                    profilePicturesList.get(currentProfilePictureIndex).backgroundColor = red;
                    canvas.repaint();
                }
                if (e.getKeyChar() == '@') {
                    profilePicturesList.get(currentProfilePictureIndex).backgroundColor = orange;
                    canvas.repaint();
                }
                if (e.getKeyChar() == '#') {
                    profilePicturesList.get(currentProfilePictureIndex).backgroundColor = yellow;
                    canvas.repaint();
                }
                if (e.getKeyChar() == '$') {
                    profilePicturesList.get(currentProfilePictureIndex).backgroundColor = green;
                    canvas.repaint();
                }
                if (e.getKeyChar() == '%') {
                    profilePicturesList.get(currentProfilePictureIndex).backgroundColor = blue;
                    canvas.repaint();
                }
                if (e.getKeyChar() == '^') {
                    profilePicturesList.get(currentProfilePictureIndex).backgroundColor = purple;
                    canvas.repaint();
                }
                if (e.getKeyChar() == '&') {
                    profilePicturesList.get(currentProfilePictureIndex).backgroundColor = Color.WHITE;
                    canvas.repaint();
                }

                if (e.getKeyChar() == 'i') {
                    profilePicturesList.get(currentProfilePictureIndex).invert();
                    canvas.repaint();
                }
                // 37- 40 arrow keys
            }
    
            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
    }

 

    public void paint(Graphics g) {
        g.setColor(profilePicturesList.get(currentProfilePictureIndex).backgroundColor);
        g.fillRect(0, 0, canvasSize, canvasSize);
        
        g.setColor(profilePicturesList.get(currentProfilePictureIndex).primaryColor);
        
        Random random = new Random();
        int numberOfBoxes = canvasSize / boxSize;
        for (int x = 0; x < numberOfBoxes; x++) {
            for (int y = 0; y < numberOfBoxes; y++) {
                if (profilePicturesList.get(currentProfilePictureIndex).map[x][y]) {
                    g.fillRect(x*boxSize, y*boxSize, boxSize, boxSize);
                }
            }
        }
    }

    public void addProfilePictureToMemory() {
        if (profilePicturesList.size() > memoryLength) {
            profilePicturesList.add(profilePicturesList.size(), makeProfilePicture());
            profilePicturesList.remove(0);
            currentProfilePictureIndex = memoryLength;
        }
        else {
            profilePicturesList.add(profilePicturesList.size(), makeProfilePicture());
            currentProfilePictureIndex = profilePicturesList.size()-1;
        }
    }

    public static ProfilePicture makeProfilePicture() {
        return new ProfilePicture(canvasSize / boxSize, boxSize, randomColor(), Color.WHITE);
    }

    public static Color randomColor() {
        Random random = new Random();
        int randomInt = random.nextInt(6) + 1;
        if (randomInt == 1) {
            return red;
        }
        if (randomInt == 2) {
            return orange;
        }
        if (randomInt == 3) {
            return yellow;
        }
        if (randomInt == 4) {
            return green;
        }
        if (randomInt == 5) {
            return blue;
        }
        if (randomInt == 6) {
            return purple;
        }
        System.out.println("We have a large problem");
        return Color.WHITE;
    }

    public void exportImage(String imageName) {
        BufferedImage image = new  BufferedImage(canvasSize, canvasSize, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics = image.createGraphics();
        paint(graphics);
        graphics.dispose();
        try {
            System.out.println("Exporting image: " + imageName + ".png");
            FileOutputStream out = new FileOutputStream(imageName + ".png");
            ImageIO.write(image, "png", out);
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }    
    }
}
