package pl.piekoszek.gorskimatches.image;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.io.IOException;


@RestController
@RequestMapping("api/number")
class ImageController {

    private final LineImageCreator lineImageCreator;


    ImageController(LineImageCreator lineImageCreator){
        this.lineImageCreator = lineImageCreator;
    }
    @GetMapping(value = "{number}", produces = MediaType.IMAGE_PNG_VALUE)
        byte[] getImage(@PathVariable int number) throws IOException {

        return lineImageCreator.create(number);
    }
}

