package pl.piekoszek.gorskimatches;

import ch.qos.logback.core.rolling.helper.TokenConverter;
import org.apache.el.parser.Token;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pl.piekoszek.gorskimatches.token.AccountInfo;
import pl.piekoszek.gorskimatches.token.EmailService;
import pl.piekoszek.gorskimatches.token.EmailServiceImplemented;
import pl.piekoszek.gorskimatches.token.TokenCreator;

import javax.mail.MessagingException;

@SpringBootApplication
public class GorskiMatchesApplication {

    public GorskiMatchesApplication() {
    }

    public static void main(String[] args) throws MessagingException {
        SpringApplication.run(GorskiMatchesApplication.class, args);
    }
}
