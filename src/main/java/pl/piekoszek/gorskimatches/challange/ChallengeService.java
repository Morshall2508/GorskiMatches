package pl.piekoszek.gorskimatches.challange;

import org.springframework.stereotype.Service;
import pl.piekoszek.gorskimatches.equation.EquationRandomizer;
import pl.piekoszek.gorskimatches.token.EmailService;

import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ChallengeService {

    private EmailService emailService;

    private EquationRandomizer equationRandomizer;

    private final GenerateUUID generateUUID;

    private ChallengeRepository challengeRepository;

    public ChallengeService(EmailService emailService, EquationRandomizer equationRandomizer, GenerateUUID generateUUID, ChallengeRepository challengeRepository) {
        this.emailService = emailService;
        this.equationRandomizer = equationRandomizer;
        this.generateUUID = generateUUID;
        this.challengeRepository = challengeRepository;
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

    public UUID createChallenge() {
        var challenge = new Challenge();
        challenge.setChallengeQuizzes(
                equationRandomizer.equationsForChallenge().stream()
                        .map(quiz -> new ChallengeQuiz(challenge, quiz))
                        .collect(Collectors.toList()));
        challenge.setUuid(generateUUID.generateUUID());
        challengeRepository.save(challenge);
        return challenge.getUuid();
    }
}
