package pl.piekoszek.gorskimatches.token;

import org.springframework.stereotype.Component;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@Component
public class HTMLEmailSender {
    private AccountInfo accountInfo;

    HTMLEmailSender(AccountInfo accountInfo) {
        this.accountInfo = accountInfo;
    }

    {
        String emailTo = "maciej.gorski.93@gmail.com";
//        String emailTo = accountInfo.getEmail();
        String emailFrom = "gorskimatchesserver@gmail.com";
        String host = "smtp.gmail.com";
        Properties properties = System.getProperties();

        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");

        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {

            protected PasswordAuthentication getPasswordAuthentication() {

                return new PasswordAuthentication("gorskimatchesserver@gmail.com", "gorskiMatches_Server123?!");
            }
        });

        session.setDebug(true);

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(emailFrom));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(emailTo));
            message.setSubject("Account Registration");
            message.setContent(
                    "<h1>Thank you for registering, have fun solving quizzes</h1>", "text/html");
            Transport.send(message);

        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }
}