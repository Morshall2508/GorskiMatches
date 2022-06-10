package pl.piekoszek.gorskimatches;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pl.piekoszek.gorskimatches.token.AccountInfo;
import pl.piekoszek.gorskimatches.token.EmailService;
import pl.piekoszek.gorskimatches.token.EmailServiceImplemented;

import javax.mail.MessagingException;

@SpringBootApplication
public class GorskiMatchesApplication {

    private static EmailServiceImplemented emailServiceImplemented;
    private static AccountInfo accountInfo;
    public GorskiMatchesApplication(EmailServiceImplemented emailServiceImplemented, AccountInfo accountInfo) {
        this.emailServiceImplemented = emailServiceImplemented;
        this.accountInfo = accountInfo;
    }


    public static void main(String[] args) throws MessagingException {
        SpringApplication.run(GorskiMatchesApplication.class, args);
//        emailServiceImplemented.sendSimpleMessage("maciej.gorski.93@gmail.com", "Email napisany w javie", "wygląda na normalny email ");
    }

}
