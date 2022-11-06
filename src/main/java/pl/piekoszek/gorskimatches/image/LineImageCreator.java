package pl.piekoszek.gorskimatches.image;

import org.springframework.stereotype.Component;

import java.awt.*;
import java.awt.image.BufferedImage;

@Component
public class LineImageCreator {

    LineInfo[][] lineInfosByNumberOrSign = new LineInfo[62][];

    {

        var line0 = new LineInfo(60, 20, true); //Lines from 0-6 are responsible for numbers
        var line1 = new LineInfo(20, 40, false);
        var line2 = new LineInfo(60, 320, true);
        var line3 = new LineInfo(360, 40, false);
        var line4 = new LineInfo(20, 340, false);
        var line5 = new LineInfo(60, 620, true);
        var line6 = new LineInfo(360, 340, false);
        var line7 = new LineInfo(60, 320, true); //minus -
        var line8 = new LineInfo(190, 190, false); //plus +
        var line9 = new LineInfo(60, 300, true); //equals top =
        var line10 = new LineInfo(60, 360, true); //equals bottom =

        lineInfosByNumberOrSign['+'] = new LineInfo[]{line7, line8}; // plus +
        lineInfosByNumberOrSign['-'] = new LineInfo[]{line7}; // minus -
        lineInfosByNumberOrSign['0'] = new LineInfo[]{line0, line1, line3, line4, line5, line6};
        lineInfosByNumberOrSign['1'] = new LineInfo[]{line3, line6};
        lineInfosByNumberOrSign['2'] = new LineInfo[]{line0, line3, line2, line4, line5};
        lineInfosByNumberOrSign['3'] = new LineInfo[]{line0, line3, line2, line6, line5};
        lineInfosByNumberOrSign['4'] = new LineInfo[]{line1, line2, line3, line6};
        lineInfosByNumberOrSign['5'] = new LineInfo[]{line0, line1, line2, line6, line5};
        lineInfosByNumberOrSign['6'] = new LineInfo[]{line0, line1, line4, line2, line5, line6};
        lineInfosByNumberOrSign['7'] = new LineInfo[]{line0, line3, line6};
        lineInfosByNumberOrSign['8'] = new LineInfo[]{line0, line1, line4, line2, line3, line5, line6};
        lineInfosByNumberOrSign['9'] = new LineInfo[]{line0, line1, line2, line3, line5, line6};
        lineInfosByNumberOrSign['='] = new LineInfo[]{line9, line10}; // equals =

    }

    public void create(char numberOrSymbol, int x, BufferedImage image, Color color) {

        for (LineInfo lineInfo : lineInfosByNumberOrSign[numberOrSymbol]) {
            if (lineInfo.horizontal) {
                drawHorizontalLine(lineInfo.x + x, lineInfo.y, image, color);
            } else {
                drawVerticalLine(lineInfo.x + x, lineInfo.y, image, color);
            }
        }
    }

    private void drawHorizontalLine(int x, int y, BufferedImage lineImage, Color color) {
        Graphics2D drawLine = lineImage.createGraphics();
        drawLine.setBackground(color);
        drawLine.clearRect(x, y, 280, 20);
    }

    private void drawVerticalLine(int x, int y, BufferedImage lineImage, Color color) {
        Graphics2D drawLine = lineImage.createGraphics();
        drawLine.setBackground(color);
        drawLine.clearRect(x, y, 20, 280);
    }

}
