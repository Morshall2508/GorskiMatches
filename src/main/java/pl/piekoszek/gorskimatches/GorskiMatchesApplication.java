package pl.piekoszek.gorskimatches;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.mail.MessagingException;

@SpringBootApplication
public class GorskiMatchesApplication {

    public GorskiMatchesApplication() {
    }

    public static void main(String[] args) throws MessagingException {
        SpringApplication.run(GorskiMatchesApplication.class, args);
    }
}
