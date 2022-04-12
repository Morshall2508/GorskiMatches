package pl.piekoszek.gorskimatches.image;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class CreateImage {
    public void create() throws IOException {
        BufferedImage imageCreate = new BufferedImage(200, 800,BufferedImage.TYPE_INT_ARGB);
        Graphics2D drawLine = imageCreate.createGraphics();
        drawLine.setBackground(Color.black);
        drawLine.clearRect(90, 50, 20, 700);
        File f = new File("C:\\Users\\macie\\IdeaProjects\\GorskiMatches\\build\\image.png");
        ImageIO.write(imageCreate,"PNG",f);
    }
}
