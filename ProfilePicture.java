package profile_picture;

import java.awt.Color;
import java.util.Random;

public class ProfilePicture {
    public Color primaryColor;
    public Color backgroundColor;
    public int numberOfBlocks;
    public int blockSize;
    public boolean[][] map;

    public ProfilePicture(int numberOfBlocks, int blockSize, Color primaryColor, Color backgroundColor) {
        this.numberOfBlocks = numberOfBlocks;
        this.blockSize = blockSize;
        this.primaryColor = primaryColor;
        this.backgroundColor = backgroundColor;
        this.makeMap();
    }

    void makeMap() {
        Random random = new Random();

        map = new boolean[numberOfBlocks][numberOfBlocks];
        int xMax = (int) Math.ceil((double) numberOfBlocks / 2);
        // int yMax = (int) Math.ceil((double)numberOfBlocks / 2);
        for (int x = 0; x < xMax; x++) {
            for (int y = 0; y < numberOfBlocks; y++) {
                map[x][y] = false;
                if (random.nextInt(2) == 1) {
                    map[x][y] = true;
                    map[numberOfBlocks - x - 1][y] = true;
                    // g.fillRect(x*boxSize, y*boxSize, boxSize, boxSize);
                    // g.fillRect((numberOfBoxes - x - 1)*boxSize, y*boxSize, boxSize, boxSize);
                    // g.fillRect(x*boxSize, (numberOfBoxes - y - 1)*boxSize, boxSize, boxSize);
                    // g.fillRect((numberOfBoxes - x - 1)*boxSize, (numberOfBoxes - y - 1)*boxSize,
                    // boxSize, boxSize);
                }
            }
        }
    }

    public void invert() {
        Color temp = backgroundColor;
        backgroundColor = primaryColor;
        primaryColor = temp;
    }
}
