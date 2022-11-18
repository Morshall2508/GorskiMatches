package pl.piekoszek.gorskimatches.challange;

import org.springframework.stereotype.Service;
import pl.piekoszek.gorskimatches.config.http.NotFoundException;
import pl.piekoszek.gorskimatches.email.EmailService;
import pl.piekoszek.gorskimatches.equation.EquationRandomizer;

import java.util.Date;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ChallengeService {

    private final EmailService emailService;

    private final EquationRandomizer equationRandomizer;

    private final GenerateUUID generateUUID;

    private final ChallengeRepository challengeRepository;

    private ChallengeDate challengeDate;
    private final Judge judge;

    public ChallengeService(EmailService emailService,
                            EquationRandomizer equationRandomizer,
                            GenerateUUID generateUUID,
                            ChallengeRepository challengeRepository,
                            Judge judge,
                            ChallengeDate challengeDate) {
        this.emailService = emailService;
        this.equationRandomizer = equationRandomizer;
        this.generateUUID = generateUUID;
        this.challengeRepository = challengeRepository;
        this.judge = judge;
        this.challengeDate = challengeDate;
    }

    void resultForRegisteredUser(ChallengeResult challengeResult) {
        var challengeInfo = getChallenge(challengeResult);
        String subject = "Challenge: " + challengeResult.getUuid() + " result";
        if (judge.getResultForChallengeUser(
                challengeInfo.getRegisteredUserScore(),
                challengeInfo.getNonRegisteredUserScore(),
                challengeInfo.getRegisteredUserTimeSeconds(),
                challengeInfo.getNonRegisteredUserTimeSeconds()) == Result.USER_1_WIN) {
            emailService.sendEmail(challengeInfo.getEmail(), subject, "Congratulations you've won!");
        } else {
            emailService.sendEmail(challengeInfo.getEmail(), subject, "Unfortunately you've lost :(");
        }
    }

    String resultForNonRegisteredUser(UUID uuid) {
        var challengeInfoData = challengeRepository.findById(uuid);
        var challengeInfo = challengeInfoData.orElseThrow(() -> new NotFoundException("Couldn't find the challenge with uuid: " + uuid));
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

    UUID createChallenge() {
        var challenge = new Challenge();
        challenge.setChallengeQuizzes(
                equationRandomizer.equationsForChallenge().stream()
                        .map(quiz -> new ChallengeQuiz(challenge, quiz))
                        .collect(Collectors.toList()));
        challenge.setUuid(generateUUID.generateUUID());
        challenge.setCreationDate(challengeDate.currentDate());
        challenge.setCreationTime(challengeDate.currentDate());
        challengeRepository.save(challenge);
        return challenge.getUuid();
    }

    void saveUser1ScoreAndAnswers(UUID uuid, ChallengeScoreAndAnswers challengeScoreAndAnswers) {
        var challenge = challengeRepository.findById(uuid).orElseThrow(() -> new NotFoundException("Couldn't find the challenge with uuid: " + uuid));
        for (int i = 0; i < 5; i++) {
            var challengeQuiz = challenge.getChallengeQuizzes().get(i);
            challengeQuiz.setAnswerUser1(challengeScoreAndAnswers.getAnswerUser1().get(i));
            challengeQuiz.setScoreUser1(challengeScoreAndAnswers.getScoreUser1().get(i));
        }
        challengeRepository.save(challenge);
    }

    void saveUser2ScoreAndAnswers(UUID uuid, ChallengeScoreAndAnswers challengeScoreAndAnswers) {
        var challenge = challengeRepository.findById(uuid).orElseThrow(() -> new NotFoundException("Couldn't find the challenge with uuid: " + uuid));
        for (int i = 0; i < 5; i++) {
            var challengeQuiz = challenge.getChallengeQuizzes().get(i);
            challengeQuiz.setAnswerUser2(challengeScoreAndAnswers.getAnswerUser2().get(i));
            challengeQuiz.setScoreUser2(challengeScoreAndAnswers.getScoreUser2().get(i));
        }
        challengeRepository.save(challenge);
    }

    List<String> getQuizzes(UUID uuid) {
        var challengeOptional = challengeRepository.findById(uuid);
        if (challengeOptional.isEmpty()) {
            return Collections.emptyList();
        }
        return challengeOptional.get().getChallengeQuizzes().stream()
                .map(ChallengeQuiz::getQuiz)
                .collect(Collectors.toList());
    }

    void saveNonRegisteredUserResult(ChallengeResult challengeResult) {
        var challengeInfo = getChallenge(challengeResult);
        challengeInfo.setNonRegisteredUserScore(challengeResult.getScore());
        challengeInfo.setNonRegisteredUserTimeSeconds(challengeResult.getTime());
        challengeRepository.save(challengeInfo);
    }

    void saveRegisteredUserResult(ChallengeResult challengeResult, String email) {
        var challengeInfo = getChallenge(challengeResult);
        challengeInfo.setEmail(email);
        challengeInfo.setRegisteredUserScore(challengeResult.getScore());
        challengeInfo.setRegisteredUserTimeSeconds(challengeResult.getTime());
        challengeRepository.save(challengeInfo);
    }

    private Challenge getChallenge(ChallengeResult challengeResult) {
        var challengeInfoData = challengeRepository.findById(challengeResult.getUuid());
        return challengeInfoData.orElseThrow(() -> new NotFoundException("Couldn't find the challenge with uuid: " + challengeResult.getUuid()));
    }

    List<Challenge> getChallenges() {
        return challengeRepository.findAll();
    }

    public Challenge getChallenge(UUID id){
        return challengeRepository.findById(id).get();
    }
}
