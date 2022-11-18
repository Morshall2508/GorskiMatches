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

    private Judge judge;


    public ChallengeService(EmailService emailService,
                            EquationRandomizer equationRandomizer,
                            GenerateUUID generateUUID,
                            ChallengeRepository challengeRepository,
                            Judge judgeResult) {
        this.emailService = emailService;
        this.equationRandomizer = equationRandomizer;
        this.generateUUID = generateUUID;
        this.challengeRepository = challengeRepository;
        this.judge = judgeResult;
    }

    public void resultForRegisteredUser(ChallengeResult challengeResult) {
        var challengeInfo = getChallenge(challengeResult);
        if (judge.getResultForChallengeUser(
                challengeInfo.getRegisteredUserScore(),
                challengeInfo.getNonRegisteredUserScore(),
                challengeInfo.getRegisteredUserTimeSeconds(),
                challengeInfo.getNonRegisteredUserTimeSeconds()) == Result.USER_1_WIN) {

            emailService.sendResultOfChallenge(challengeInfo.getEmail(), "Congratulations you've won!", challengeResult.getUuid());
        } else {
            emailService.sendResultOfChallenge(challengeInfo.getEmail(), "Unfortunately you've lost :(", challengeResult.getUuid());
        }
    }

    public String resultForNonRegisteredUser(UUID uuid) {
        var challengeInfoData = challengeRepository.findById(uuid);
        var challengeInfo = challengeInfoData.get();
        if (judge.getResultForChallengeUser(
                challengeInfo.getRegisteredUserScore(),
                challengeInfo.getNonRegisteredUserScore(),
                challengeInfo.getRegisteredUserTimeSeconds(),
                challengeInfo.getNonRegisteredUserTimeSeconds()) == Result.USER_2_WIN) {

            return "Congratulations you've won!";
        } else {
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
        var challenge = challengeRepository.findById(uuid).get();
        for (int i = 0; i < 5; i++) {
            var challengeQuiz = challenge.getChallengeQuizzes().get(i);
            challengeQuiz.setAnswerUser1(challengeHistory.getAnswerUser1().get(i));
            challengeQuiz.setScoreUser1(challengeHistory.getScoreUser1().get(i));
        }
        challengeRepository.save(challenge);
    }

    public void saveUser2ScoreAndAnswers(UUID uuid, ChallengeHistory challengeHistory) {
        var challenge = challengeRepository.findById(uuid).get();
        for (int i = 0; i < 5; i++) {
            var challengeQuiz = challenge.getChallengeQuizzes().get(i);
            challengeQuiz.setAnswerUser2(challengeHistory.getAnswerUser2().get(i));
            challengeQuiz.setScoreUser2(challengeHistory.getScoreUser2().get(i));
        }
        challengeRepository.save(challenge);
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


    public List<Challenge> getChallenges() {
        return challengeRepository.findAll();
    }

    public Challenge getChallenge(UUID id){
        return challengeRepository.findById(id).get();
    }
}
