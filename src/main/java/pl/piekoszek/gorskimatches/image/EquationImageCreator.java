package pl.piekoszek.gorskimatches.image;

import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

@Component
class EquationImageCreator {

    private final LineImageCreator lineImageCreator;

    EquationImageCreator(LineImageCreator lineImageCreator) {
        this.lineImageCreator = lineImageCreator;
    }

    byte[] createImage(String equation, Color color, int imageType, int width, int height, String formatName) throws IOException {
        BufferedImage equationImage = new BufferedImage(width, height, imageType);
        for (int i = 0; i < equation.length(); i++) {
            char numberOrSign = equation.charAt(i);
            lineImageCreator.create(numberOrSign, i * 400, equationImage, color);
        }
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(equationImage, formatName, baos);
        return baos.toByteArray();
    }

    byte[] addPadding(byte[] image, int padding, String formatName) throws IOException {
        InputStream is = new ByteArrayInputStream(image);
        BufferedImage oldImage = ImageIO.read(is);
        BufferedImage newImage = new BufferedImage(oldImage.getWidth() + padding * 2, oldImage.getHeight() + padding * 2, oldImage.getType());
        Graphics2D g = (Graphics2D) newImage.getGraphics();
        g.drawImage(oldImage, padding, padding, null);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(newImage, formatName, baos);
        return baos.toByteArray();
    }
}
