package pl.piekoszek.gorskimatches.challange;

import org.springframework.stereotype.Service;
import pl.piekoszek.gorskimatches.equation.EquationRandomizer;
import pl.piekoszek.gorskimatches.token.EmailService;

import java.util.List;
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

    public void resultForRegisteredUser(ChallengeResult challengeResult) {
        var challengeInfo = getChallenge(challengeResult);
        if (challengeInfo.getRegisteredUserScore() != challengeInfo.getNonRegisteredUserScore()) {
            if (challengeInfo.getRegisteredUserScore() > challengeInfo.getNonRegisteredUserScore()) {
                emailService.sendResultOfChallenge(challengeInfo.getEmail(), "Congratulations you've won!", challengeResult.getUuid());
            }
            emailService.sendResultOfChallenge(challengeInfo.getEmail(), "Unfortunately you've lost :(", challengeResult.getUuid());
        }
        if (challengeInfo.getRegisteredUserTimeSeconds() < challengeInfo.getNonRegisteredUserTimeSeconds()) {
            emailService.sendResultOfChallenge(challengeInfo.getEmail(), "Congratulations you've won!", challengeResult.getUuid());
        } else {
            emailService.sendResultOfChallenge(challengeInfo.getEmail(), "Unfortunately you've lost :(", challengeResult.getUuid());
        }
    }

    public String resultForNonRegisteredUser(UUID uuid) {
        var challengeInfoData = challengeRepository.findById(uuid);
        var challengeInfo = challengeInfoData.get();
        if (challengeInfo.getRegisteredUserScore() != challengeInfo.getNonRegisteredUserScore()) {
            if (challengeInfo.getNonRegisteredUserScore() > challengeInfo.getRegisteredUserScore()) {
                return "Congratulations you've won!";
            }
            return "Unfortunately you've lost :(";
        }
        if (challengeInfo.getNonRegisteredUserTimeSeconds() < challengeInfo.getRegisteredUserTimeSeconds()) {
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

    public List<String> getQuizzes(UUID uuid) {
        return challengeRepository.findById(uuid).get().getChallengeQuizzes().stream()
                .map(ChallengeQuiz::getQuiz)
                .collect(Collectors.toList());
    }

    public void saveNonRegisteredUserResult(ChallengeResult challengeResult) {
        var challengeInfo = getChallenge(challengeResult);
        challengeInfo.setNonRegisteredUserScore(challengeResult.getScore());
        challengeInfo.setNonRegisteredUserTimeSeconds(challengeResult.getTime());
        challengeRepository.save(challengeInfo);
    }

    public void saveRegisteredUserResult(ChallengeResult challengeResult, String email) {
        var challengeInfo = getChallenge(challengeResult);
        challengeInfo.setEmail(email);
        challengeInfo.setRegisteredUserScore(challengeResult.getScore());
        challengeInfo.setRegisteredUserTimeSeconds(challengeResult.getTime());
        challengeRepository.save(challengeInfo);
    }

    private Challenge getChallenge(ChallengeResult challengeResult) {
        var challengeInfoData = challengeRepository.findById(challengeResult.getUuid());
        return challengeInfoData.get();
    }
}
