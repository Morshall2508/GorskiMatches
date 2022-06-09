package pl.piekoszek.gorskimatches;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestEndpoint {

    private final String url;

    public TestEndpoint(@Value("${mail.url}") String url) {
        this.url = url;
    }

    @GetMapping
    String test() {
        return url;
    }

}
