package pl.piekoszek.gorskimatches.email;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@Service
public class EmailService {

    private final JavaMailSender mailSender;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendEmail(String to, String subject, String text) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        message.setContent(text, "text/html");
        message.addRecipients(Message.RecipientType.TO, to);
        message.setSubject(subject);
        message.setFrom(new InternetAddress("gorskimatchesserver@gmail.com"));
        mailSender.send(message);
    }
}