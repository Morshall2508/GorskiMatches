package pl.piekoszek.gorskimatches.image;

import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
@Component
public class LineImageCreator {
    public byte[] create() throws IOException {
        BufferedImage lineImage = new BufferedImage(200, 800,BufferedImage.TYPE_INT_ARGB);
        Graphics2D drawer = lineImage.createGraphics();
        drawer.setBackground(Color.black);
        drawer.clearRect(90, 50, 20, 700);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(lineImage, "png", baos);
        return baos.toByteArray();
    }
}