package pl.piekoszek.gorskimatches.challange;

import org.springframework.stereotype.Service;
import pl.piekoszek.gorskimatches.equation.EquationRandomizer;
import pl.piekoszek.gorskimatches.token.EmailService;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ChallengeService {

    private EmailService emailService;

    private EquationRandomizer equationRandomizer;

    private final GenerateUUID generateUUID;

    private ChallengeRepository challengeRepository;

    private ChallengeQuiz challengeQuiz;

    public ChallengeService(EmailService emailService,
                            EquationRandomizer equationRandomizer,
                            GenerateUUID generateUUID,
                            ChallengeRepository challengeRepository,
                            ChallengeQuiz challengeQuiz) {
        this.emailService = emailService;
        this.equationRandomizer = equationRandomizer;
        this.generateUUID = generateUUID;
        this.challengeRepository = challengeRepository;
        this.challengeQuiz = challengeQuiz;
    }

    public void resultForRegisteredUser(ChallengeResult challengeResult) {
        var challengeInfo = getChallenge(challengeResult);
        if (challengeInfo.getRegisteredUserScore() != challengeInfo.getNonRegisteredUserScore()) {
            if (challengeInfo.getRegisteredUserScore() > challengeInfo.getNonRegisteredUserScore()) {
                emailService.sendResultOfChallenge(challengeInfo.getEmail(), "Congratulations you've won!", challengeResult.getUuid());
            } else {
                emailService.sendResultOfChallenge(challengeInfo.getEmail(), "Unfortunately you've lost :(", challengeResult.getUuid());
            }
        } else {
            if (challengeInfo.getRegisteredUserTimeSeconds() < challengeInfo.getNonRegisteredUserTimeSeconds()) {
                emailService.sendResultOfChallenge(challengeInfo.getEmail(), "Congratulations you've won!", challengeResult.getUuid());
            } else {
                emailService.sendResultOfChallenge(challengeInfo.getEmail(), "Unfortunately you've lost :(", challengeResult.getUuid());
            }
        }
    }

    public String resultForNonRegisteredUser(UUID uuid) {
        var challengeInfoData = challengeRepository.findById(uuid);
        var challengeInfo = challengeInfoData.get();
        if (challengeInfo.getRegisteredUserScore() != challengeInfo.getNonRegisteredUserScore()) {
            if (challengeInfo.getNonRegisteredUserScore() > challengeInfo.getRegisteredUserScore()) {
                return "Congratulations you've won!";
            } else {
                return "Unfortunately you've lost :(";
            }
        } else {
            if (challengeInfo.getNonRegisteredUserTimeSeconds() < challengeInfo.getRegisteredUserTimeSeconds()) {
                return "Congratulations you've won!";
            }
            return "Unfortunately you've lost :(";
        }
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

    public void saveUser1ScoreAndAnswers(UUID uuid, ChallengeHistory challengeHistory) {
        var challengeInfo = challengeRepository.findById(uuid).get();
        for (int i = 0; i < 5; i++) {
            var quiz = challengeInfo.getChallengeQuizzes().get(i);
            quiz.setAnswerUser1(challengeHistory.getAnswerUser1().get(i));
            quiz.setScoreUser1(challengeHistory.getScoreUser1().get(i));
        }
        challengeRepository.save(challengeInfo);
    }

    public void saveUser2ScoreAndAnswers(UUID uuid, ChallengeHistory challengeHistory) {
        var challengeInfo = challengeRepository.findById(uuid).get();
        for (int i = 0; i < 5; i++) {
            var quiz = challengeInfo.getChallengeQuizzes().get(i);
            quiz.setAnswerUser2(challengeHistory.getAnswerUser2().get(i));
            quiz.setScoreUser2(challengeHistory.getScoreUser2().get(i));
        }
        challengeRepository.save(challengeInfo);
    }

    public List<String> getUser1Answers(UUID uuid) {
        return challengeRepository.findById(uuid).get().getChallengeQuizzes().stream()
                .map(ChallengeQuiz::getAnswerUser1)
                .collect(Collectors.toList());
    }

    public List<Integer> getUser1Score(UUID uuid) {
        return challengeRepository.findById(uuid).get().getChallengeQuizzes().stream()
                .map(ChallengeQuiz::getScoreUser1)
                .collect(Collectors.toList());
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

    public Challenge getChallenge(ChallengeResult challengeResult) {
        var challengeInfoData = challengeRepository.findById(challengeResult.getUuid());
        return challengeInfoData.get();
    }

    public List<UUID> getChallengesUUIDs() {
        return challengeRepository.findAll().stream()
                .map(Challenge::getUuid)
                .collect(Collectors.toList());
    }

    public List<ChallengeQuiz> getInfoChallengeQuiz() {
        List<ChallengeQuiz> dupsko = new ArrayList<>();
        var dupeczka =  challengeRepository.findAll();
        for (Challenge challenge: dupeczka) {
            var dupska = challenge.getChallengeQuizzes().stream().toList();
            dupsko.addAll(dupska);
        }
        return dupsko;
    }

    public List<Challenge> getChallenges() {
        return new ArrayList<>(challengeRepository.findAll());
    }
}
