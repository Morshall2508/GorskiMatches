package pl.piekoszek.gorskimatches.token;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

import javax.mail.Transport;


@Component
public class EmailServiceImplemented {

    private EmailService emailService;
    private AccountInfo accountInfo;
    private TokenCreator tokenCreator;
    public EmailServiceImplemented(EmailService emailService, AccountInfo accountInfo,TokenCreator tokenCreator) {
        this.emailService = emailService;
        this.accountInfo = accountInfo;
        this.tokenCreator = tokenCreator;
    }

    public void sendRegistrationLink(String to) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("gorskimatchesserver@gmail.com");
        message.setTo(to);
        message.setSubject("Account registration");
        message.setText("To register click on this link:" + "http://localhost:8080/auth/login.html?token=" + TokenCreator.jwtToken(to));
        emailService.getJavaMailSender().send(message);
    }
}
