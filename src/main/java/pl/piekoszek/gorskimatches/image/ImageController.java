package pl.piekoszek.gorskimatches.image;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.io.IOException;

@RestController
@RequestMapping("api")
class ImageController {
    @RequestMapping(value = "image", produces = MediaType.IMAGE_PNG_VALUE)
    byte[] getImage() throws IOException {
        var createImage = new LineImageCreator();
        return createImage.create();
    }
}

