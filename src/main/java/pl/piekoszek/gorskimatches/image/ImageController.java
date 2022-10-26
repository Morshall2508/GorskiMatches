package pl.piekoszek.gorskimatches.image;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.awt.*;
import java.io.IOException;

@RestController
@RequestMapping("api/image/equation")
class ImageController {

    private final EquationImageCreator equationCreator;


    ImageController(EquationImageCreator equationCreator) {
        this.equationCreator = equationCreator;
    }

    @GetMapping(value = "{equation}", produces = MediaType.IMAGE_PNG_VALUE)
    byte[] getImage(@PathVariable() String equation) throws IOException {

        return equationCreator.createImageForWebsite(equation, Color.white);
    }
    @GetMapping(value = "fb/{equation}", produces = MediaType.IMAGE_JPEG_VALUE)
    byte[] getImageForFacebook(@PathVariable() String equation) throws IOException {

        return equationCreator.createImageForFacebook(equation, Color.white);
    }
}

