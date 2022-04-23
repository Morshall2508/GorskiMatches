package pl.piekoszek.gorskimatches.image;

import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
@Component
public class LineImageCreator {

    int[][] newArray = new int[10][];

    public void newArray() {
        newArray[0] = new int[]{0,1,2,4,5,6};
        newArray[1] = new int[]{1,4};
        newArray[2] = new int[]{0,2,3,4,5};
        newArray[3] = new int[]{0,2,3,5};
        newArray[4] = new int[]{1,2,3,6};
        newArray[5] = new int[]{0,1,3,5,6};
        newArray[6] = new int[]{0,1,3,4,5,6};
        newArray[7] = new int[]{0,2,6};
        newArray[8] = new int[]{0,1,2,3,4,5,6,};
        newArray[9] = new int[]{1,2,3,5,6};
        }

    public byte[] create() throws IOException {
        BufferedImage lineImage = new BufferedImage(200, 800,BufferedImage.TYPE_INT_ARGB);


//
//        Graphics2D drawer = lineImage.createGraphics();
//        drawer.setBackground(Color.black);
//        drawer.clearRect(90, 50, 20, 700);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(lineImage, "png", baos);
        return baos.toByteArray();
    }
//
    private Graphics2D singleLineDrawer(int x, int y, int width, int height) {
        Graphics2D drawer = singleLineDrawer(x,y,width,height);
        drawer.setBackground(Color.black);
        drawer.clearRect (x,y,width,height);
        return drawer;
    }

}
