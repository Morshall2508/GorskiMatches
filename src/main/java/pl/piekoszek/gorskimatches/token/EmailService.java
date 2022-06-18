package pl.piekoszek.gorskimatches.token;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;


@Service
public class EmailService {

    private final JavaMailSender mailSender;
    private final String server;

    EmailService(JavaMailSender mailSender, @Value("${matches.server.address}") String server) {
        this.mailSender = mailSender;
        this.server = server;
    }

    public void sendAccountActivationLink(String to) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("gorskimatchesserver@gmail.com");
        message.setTo(to);
        message.setSubject("Account registration");
        message.setText("To register click on this link: " + server + "auth/login.html?token=" + TokenCreator.jwtToken(to));
        mailSender.send(message);
    }
}