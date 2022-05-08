package pl.piekoszek.gorskimatches.image;

import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Component
public class EquationCreator {

    String[] randomEquation = new String[]{"9+2=6", "5+7=2", "9+3=5", "4+6=4", "5+2=8"};

    private final LineImageCreator lineImageCreator;

    EquationCreator(LineImageCreator lineImageCreator) {
        this.lineImageCreator = lineImageCreator;
    }

    public byte[] create(String equation) throws IOException {
        BufferedImage equationImage = new BufferedImage(2000, 660, BufferedImage.TYPE_INT_ARGB);
        for (int i = 0; i < equation.length(); i++) {
            char numberOrSign = equation.charAt(i);
            lineImageCreator.create(numberOrSign, i * 400, equationImage);
        }
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(equationImage, "png", baos);
        return baos.toByteArray();
    }
}
