package pl.piekoszek.gorskimatches.token;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;


@Component
public class EmailServiceImplemented {

    private EmailService emailService;
    private AccountInfo accountInfo;
    public EmailServiceImplemented(EmailService emailService, AccountInfo accountInfo) {
        this.emailService = emailService;
        this.accountInfo = accountInfo;
    }

    public void sendSimpleMessage(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("gorskimatchesserver@gmail.com");
        message.setTo(accountInfo.getEmail());
        message.setSubject(subject);
        message.setText(text);
        emailService.getJavaMailSender().send(message);
    }
}
