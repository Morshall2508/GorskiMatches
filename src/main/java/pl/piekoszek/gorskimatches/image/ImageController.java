package pl.piekoszek.gorskimatches.image;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

@RestController
@RequestMapping("api/image/equation")
class ImageController {

    private final EquationImageCreator equationCreator;

    ImageController(EquationImageCreator equationCreator) {
        this.equationCreator = equationCreator;
    }

    @GetMapping(value = "{equation}", produces = MediaType.IMAGE_PNG_VALUE)
    byte[] getPngImage(@PathVariable() String equation) throws IOException {
        return equationCreator.createImage(equation, Color.white, BufferedImage.TYPE_INT_ARGB, 2000, 660, "png");
    }

    @GetMapping(value = "fb/{equation}", produces = MediaType.IMAGE_JPEG_VALUE)
    byte[] getJpegImage(@PathVariable() String equation) throws IOException {
        return equationCreator.addPadding(equationCreator.createImage(equation, Color.white, BufferedImage.TYPE_INT_RGB, 2000, 660, "jpeg"), 100, "jpeg");
    }
}

