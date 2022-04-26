package pl.piekoszek.gorskimatches.image;

import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import static java.awt.Color.black;

@Component
public class LineImageCreator {

    LineInfo[][] newArray = new LineInfo[10][];

    {
        var line0 = new LineInfo(20, 20, true);
        var line1 = new LineInfo(20, 20, false);
        var line2 = new LineInfo(20, 280, true);
        var line3 = new LineInfo(360, 20, false);
        var line4 = new LineInfo(20, 280, false);
        var line5 = new LineInfo(20, 560, true);
        var line6 = new LineInfo(360, 280, false);

        newArray[0] = new LineInfo[]{line0, line1, line3, line4, line5, line6};
        newArray[1] = new LineInfo[]{line1, line4};
        newArray[2] = new LineInfo[]{line0, line3, line2, line4, line5};
        newArray[3] = new LineInfo[]{line0, line3, line2, line6, line5};
        newArray[4] = new LineInfo[]{line1, line2, line3, line6};
        newArray[5] = new LineInfo[]{line0, line1, line2, line6, line5};
        newArray[6] = new LineInfo[]{line0, line1, line4, line2, line5, line6};
        newArray[7] = new LineInfo[]{line0, line3, line6};
        newArray[8] = new LineInfo[]{line0, line1, line4, line2, line3, line5, line6};
        newArray[9] = new LineInfo[]{line0, line1, line2, line3, line5, line6};
    }

    public byte[] create(int number) throws IOException {
        BufferedImage lineImage = new BufferedImage(400, 600, BufferedImage.TYPE_INT_ARGB);

        for (LineInfo lineInfo : newArray[number]) {
            if (lineInfo.horizontal) {
                drawHorizontalLine(lineInfo.x, lineInfo.y, lineImage);
            } else {
                drawVerticalLine(lineInfo.x, lineInfo.y, lineImage);
            }
        }

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(lineImage, "png", baos);
        return baos.toByteArray();
    }

    private void drawHorizontalLine(int x, int y, BufferedImage lineImage) {
        Graphics2D drawLine = lineImage.createGraphics();
        drawLine.setBackground(black);
        drawLine.clearRect(x, y, 360, 20);
    }

    private void drawVerticalLine(int x, int y, BufferedImage lineImage) {
        Graphics2D drawLine = lineImage.createGraphics();
        drawLine.setBackground(black);
        drawLine.clearRect(x, y, 20, 280);
    }

}
