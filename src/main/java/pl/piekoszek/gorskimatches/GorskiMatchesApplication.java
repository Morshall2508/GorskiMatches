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

    private static EmailServiceImplemented emailServiceImplemented;
    private static AccountInfo accountInfo;
    private static TokenCreator tokenCreator;

    public GorskiMatchesApplication(EmailServiceImplemented emailServiceImplemented, AccountInfo accountInfo, TokenCreator tokenCreator) {
        GorskiMatchesApplication.emailServiceImplemented = emailServiceImplemented;
        GorskiMatchesApplication.accountInfo = accountInfo;
        GorskiMatchesApplication.tokenCreator = tokenCreator;
    }

    public static void main(String[] args) throws MessagingException {
        SpringApplication.run(GorskiMatchesApplication.class, args);
        emailServiceImplemented.sendSimpleMessage(String.valueOf(accountInfo.getEmail()), "Account registration link",
                "Here is the activation link:" + "http://localhost:8080/auth.login.html?jwt=" + tokenCreator.jwtToken(accountInfo.getEmail()));
    }
}
