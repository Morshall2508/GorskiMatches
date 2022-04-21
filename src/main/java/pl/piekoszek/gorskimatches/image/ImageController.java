package pl.piekoszek.gorskimatches.image;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.io.IOException;

@RestController
@RequestMapping("api")
class ImageController {

    private final LineImageCreator lineImageCreator;

    ImageController(LineImageCreator lineImageCreator){
        this.lineImageCreator = lineImageCreator;
    }
    @RequestMapping(value = "image", produces = MediaType.IMAGE_PNG_VALUE)
    byte[] getImage() throws IOException {
        return lineImageCreator.create();
    }
}

