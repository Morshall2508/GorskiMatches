package pl.piekoszek.gorskimatches.image;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class LineImageCreator {
    public void create() throws IOException {
        BufferedImage lineImage = new BufferedImage(200, 800,BufferedImage.TYPE_INT_ARGB);
        Graphics2D drawer = lineImage.createGraphics();
        drawer.setBackground(Color.black);
        drawer.clearRect(90, 50, 20, 700);
        File file = new File("build\\line.png");
        ImageIO.write(lineImage,"PNG",file);
    }
}
