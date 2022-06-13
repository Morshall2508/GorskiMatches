package pl.piekoszek.gorskimatches.token;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;


@Component
public class EmailServiceImplemented {

    private EmailService emailService;

    public void sendRegistrationLink(String to) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("gorskimatchesserver@gmail.com");
        message.setTo(to);
        message.setSubject("Account registration");
        message.setText("To register click on this link: " + "http://localhost:8080/auth/login.html?token=" + TokenCreator.jwtToken(to));
        emailService.getJavaMailSender().send(message);
    }
}
