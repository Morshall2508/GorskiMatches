package pl.piekoszek.gorskimatches.image;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

public class ImageControler {
    class imagecontroler {
        @RestController
        @RequestMapping("api")
        class ImageController {

            @GetMapping("image")
            byte[] getImage() throws IOException {

                var bytes = createImage.create();
                return bytes;
            }

        }
    }
}
