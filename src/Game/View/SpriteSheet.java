//Kyle Pittman 2/7/2016.
package Game.View;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class SpriteSheet {
    private final int imageSize = 10;
    private BufferedImage image;

    public SpriteSheet(String path) throws IOException {
        image = ImageIO.read(new File("C:\\Java Workspace\\MazeRunner\\src\\Game\\View\\SpriteSheet.png"));
    }

    public BufferedImage getSprite(int col, int row){
        System.out.println("x: " + col + ". Y: " + row);
        return image.getSubimage(col*imageSize, row*imageSize, imageSize, imageSize);    }
}
