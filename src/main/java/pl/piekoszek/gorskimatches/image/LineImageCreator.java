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

    {
        newArray[0] = new int[]{0,1,2,4,5,6};
        newArray[1] = new int[]{1,4};
        newArray[2] = new int[]{0,2,3,4,5};
        newArray[3] = new int[]{0,2,3,5};
        newArray[4] = new int[]{1,2,3,6};
        newArray[5] = new int[]{0,1,3,5,6};
        newArray[6] = new int[]{0,1,3,4,5,6};
        newArray[7] = new int[]{0,2,6};
        newArray[8] = new int[]{0,1,2,3,4,5,6};
        newArray[9] = new int[]{0,1,2,3,5,6};

    }

    public byte[] create() throws IOException {
        BufferedImage lineImage = new BufferedImage(400, 600,BufferedImage.TYPE_INT_ARGB);
        Graphics2D drawer = lineImage.createGraphics();
        drawer.setBackground(Color.black);
        drawer.clearRect(20, 20, 360, 20); /*1st line - 0 */
        drawer.clearRect(20, 20, 20, 300); /*2nd line */
        drawer.clearRect(20, 300, 360, 20); /*3rd line */
        drawer.clearRect(380, 20, 20, 300); /*4th line */
        drawer.clearRect(20, 300, 20, 300); /*5th line */
        drawer.clearRect(20, 580, 360, 20); /*6th line */
        drawer.clearRect(380, 300, 20, 330); /*7th line */
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(lineImage, "png", baos);
        return baos.toByteArray();
    }
}

