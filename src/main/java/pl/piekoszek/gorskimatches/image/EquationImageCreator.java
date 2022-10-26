package pl.piekoszek.gorskimatches.image;

import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Component
public class EquationImageCreator {

    private final LineImageCreator lineImageCreator;

    EquationImageCreator(LineImageCreator lineImageCreator) {
        this.lineImageCreator = lineImageCreator;
    }

    public byte[] createImageForWebsite(String equation, Color color) throws IOException {
        BufferedImage equationImage = new BufferedImage(2000, 660, BufferedImage.TYPE_INT_ARGB);
        return getBytes(equation, color, equationImage);
    }
    public byte[] createImageForFacebook(String equation, Color color) throws IOException {
        BufferedImage equationImage = new BufferedImage(2000, 660, BufferedImage.TYPE_INT_RGB);
        return getBytes(equation, color, equationImage);
    }

    private byte[] getBytes(String equation, Color color, BufferedImage equationImage) throws IOException {
        for (int i = 0; i < equation.length(); i++) {
            char numberOrSign = equation.charAt(i);
            lineImageCreator.create(numberOrSign, i * 400, equationImage, color);
        }
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(equationImage, "png", baos);
        return baos.toByteArray();
    }
}
