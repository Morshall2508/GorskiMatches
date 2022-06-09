package pl.piekoszek.gorskimatches;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@SpringBootApplication
public class GorskiMatchesApplication {

	public static void main(String[] args) {
		SpringApplication.run(GorskiMatchesApplication.class, args);
	}
	{
		String emailTo = "maciej.gorski.93@gmail.com";
//        String emailTo = accountInfo.getEmail();
		String emailFrom = "gorskimatchesserver@gmail.com";
		String host = "smtp.gmail.com";
		Properties properties = System.getProperties();

		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.port", "25");
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
