package pl.piekoszek.gorskimatches.challange;

import org.springframework.stereotype.Service;
import pl.piekoszek.gorskimatches.token.EmailService;

@Service
public class ChallengeService {

    private EmailService emailService;

    public ChallengeService(EmailService emailService) {
        this.emailService = emailService;
    }

    public void resultForRegisteredUser(Challenge challenge, float registeredUserTime, float nonRegisteredUserTime, int registeredUserScore, int nonRegisteredUserScore) {
        if (registeredUserScore != nonRegisteredUserScore) {
            if (registeredUserScore > nonRegisteredUserScore) {
                emailService.sendResultOfChallenge(challenge.getEmail(), "Congratulations you've won!", challenge.getUuid());
            }
            emailService.sendResultOfChallenge(challenge.getEmail(), "Unfortunately you've lost :(", challenge.getUuid());
        }
        if (registeredUserScore == nonRegisteredUserScore) {
            if (registeredUserTime < nonRegisteredUserTime) {
                emailService.sendResultOfChallenge(challenge.getEmail(), "Congratulations you've won!", challenge.getUuid());
            }
        }
        emailService.sendResultOfChallenge(challenge.getEmail(), "Unfortunately you've lost :(", challenge.getUuid());
    }

    public String resultForNonRegisteredUser(float registeredUserTime, float nonRegisteredUserTime, int registeredScore, int nonRegisteredScore) {

        if (registeredScore != nonRegisteredScore) {
            if (nonRegisteredScore > registeredScore) {
                return "Congratulations you've won!";
            }
            return "Unfortunately you've lost :(";
        }
        if (nonRegisteredUserTime < registeredUserTime) {
            return "Congratulations you've won!";
        }
        return "Unfortunately you've lost :(";
    }
}
