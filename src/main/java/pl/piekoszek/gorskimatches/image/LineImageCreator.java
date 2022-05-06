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

    LineInfo[][] arrayOfElements = new LineInfo[62][];

    {

        var line0 = new LineInfo(20, 20, true); //Lines from 0-6 are responsible for numbers
        var line1 = new LineInfo(20, 20, false);
        var line2 = new LineInfo(20, 280, true);
        var line3 = new LineInfo(360, 20, false);
        var line4 = new LineInfo(20, 280, false);
        var line5 = new LineInfo(20, 560, true);
        var line6 = new LineInfo(360, 280, false);
        var line7 = new LineInfo(20, 280, true); //minus -
        var line8 = new LineInfo(180, 150, false); //plus +
        var line9 = new LineInfo(20, 240, true); //equals top =
        var line10 = new LineInfo(20, 320, true); //equals bottom =

        arrayOfElements['+'] = new LineInfo[]{line7, line8}; // plus +
        arrayOfElements['-'] = new LineInfo[]{line7}; // minus -
        arrayOfElements['0'] = new LineInfo[]{line0, line1, line3, line4, line5, line6};
        arrayOfElements['1'] = new LineInfo[]{line1, line4};
        arrayOfElements['2'] = new LineInfo[]{line0, line3, line2, line4, line5};
        arrayOfElements['3'] = new LineInfo[]{line0, line3, line2, line6, line5};
        arrayOfElements['4'] = new LineInfo[]{line1, line2, line3, line6};
        arrayOfElements['5'] = new LineInfo[]{line0, line1, line2, line6, line5};
        arrayOfElements['6'] = new LineInfo[]{line0, line1, line4, line2, line5, line6};
        arrayOfElements['7'] = new LineInfo[]{line0, line3, line6};
        arrayOfElements['8'] = new LineInfo[]{line0, line1, line4, line2, line3, line5, line6};
        arrayOfElements['9'] = new LineInfo[]{line0, line1, line2, line3, line5, line6};
        arrayOfElements['='] = new LineInfo[]{line9, line10}; // equals =

    }

    public void create(char number, int x, BufferedImage image) {

        for (LineInfo lineInfo : arrayOfElements[number]) {
            if (lineInfo.horizontal) {
                drawHorizontalLine(lineInfo.x + x, lineInfo.y, image);
            } else {
                drawVerticalLine(lineInfo.x + x, lineInfo.y, image);
            }
        }
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
