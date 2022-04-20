package pl.piekoszek.gorskimatches.image;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("api")
class ImageController {

    @RequestMapping(value = "image",method = RequestMethod.GET, produces = MediaType.IMAGE_PNG_VALUE)

    byte[] getImage() throws IOException {
        var createImage = new LineImageCreator();
        var bytes = createImage.create();

        return bytes;

    }

}

