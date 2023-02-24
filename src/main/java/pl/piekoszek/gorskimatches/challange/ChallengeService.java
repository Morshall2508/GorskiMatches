package pl.piekoszek.gorskimatches.challange;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import pl.piekoszek.gorskimatches.config.http.NotFoundException;
import pl.piekoszek.gorskimatches.email.EmailService;

import javax.mail.MessagingException;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ChallengeService {

    private final EmailService emailService;

    private final ChallengeRepository challengeRepository;

    private final Judge judge;

    private final String server;

    public ChallengeService(EmailService emailService,
                            ChallengeRepository challengeRepository,
                            Judge judge,
                            @Value("${matches.server.address}") String server) {
        this.emailService = emailService;
        this.challengeRepository = challengeRepository;
        this.judge = judge;
        this.server = server;
    }

    void saveUserWithEmailAndSendEmail(ChallengeResult challengeResult, String email) throws MessagingException {
        if (checkIfQuizHasBeenCompletedByUser1(challengeResult.getUuid())) {
            saveChallengeInformationForUser2(challengeResult.getUuid(), challengeResult, email);
            determineResultAndSendEmail(challengeResult);
        } else {
            saveChallengeInformationForUser1(challengeResult.getUuid(), challengeResult, email);
        }
    }

    void saveUserWithoutEmailAndSendEmail(ChallengeResult challengeResult) throws MessagingException {
        saveChallengeInformationForUser2(challengeResult.getUuid(), challengeResult, null);
        determineResultAndSendEmail(challengeResult);
    }

    void saveChallengeInformationForUser1(UUID uuid, ChallengeResult challengeResult, String email) {
        var challenge = challengeRepository.findById(uuid).orElseThrow(() -> new NotFoundException("Couldn't find the challenge with uuid: " + uuid));
        saveAnswersAndScoreForUser1(challengeResult, challenge);
        challenge.setEmailUser1(email);
        challenge.setTimeToSolveUser1(challengeResult.getTimeToSolveChallenge());
        challenge.setTotalScoreUser1(challenge.getChallengeQuizzes().stream().mapToInt(ChallengeQuiz::getScoreUser1).sum());
        challengeRepository.save(challenge);
    }

    void saveChallengeInformationForUser2(UUID uuid, ChallengeResult challengeResult, String email) {
        var challenge = challengeRepository.findById(uuid).orElseThrow(() -> new NotFoundException("Couldn't find the challenge with uuid: " + uuid));
        if (email != null) {
            saveAnswersAndScoreForUser2(challengeResult, challenge);
            challenge.setEmailUser2(email);
            challenge.setTimeToSolveUser2(challengeResult.getTimeToSolveChallenge());
            challenge.setTotalScoreUser2(challenge.getChallengeQuizzes().stream().mapToInt(ChallengeQuiz::getScoreUser2).sum());
            challengeRepository.save(challenge);
        } else {
            saveAnswersAndScoreForUser2(challengeResult, challenge);
            challenge.setTimeToSolveUser2(challengeResult.getTimeToSolveChallenge());
            challenge.setTotalScoreUser2(challenge.getChallengeQuizzes().stream().mapToInt(ChallengeQuiz::getScoreUser2).sum());
            challengeRepository.save(challenge);
        }
    }

    void saveAnswersAndScoreForUser1(ChallengeResult challengeResult, Challenge challenge) {
        for (int i = 0; i < 5; i++) {
            var challengeQuiz = challenge.getChallengeQuizzes().get(i);
            challengeQuiz.setAnswerUser1(challengeResult.getAnswersForChallenge().get(i));
            challengeQuiz.setScoreUser1(judge.verifyAnswerToQuiz(challengeQuiz.quiz, challengeResult.getAnswersForChallenge().get(i)) ? 1 : 0);
        }
    }

    void saveAnswersAndScoreForUser2(ChallengeResult challengeResult, Challenge challenge) {
        for (int i = 0; i < 5; i++) {
            var challengeQuiz = challenge.getChallengeQuizzes().get(i);
            challengeQuiz.setAnswerUser2(challengeResult.getAnswersForChallenge().get(i));
            challengeQuiz.setScoreUser2(judge.verifyAnswerToQuiz(challengeQuiz.quiz, challengeResult.getAnswersForChallenge().get(i)) ? 1 : 0);
        }
    }

    boolean resultForUserWithoutEmail(UUID uuid) {
        var challengeInfoData = challengeRepository.findById(uuid);
        var challengeInfo = challengeInfoData.orElseThrow(() -> new NotFoundException("Couldn't find the challenge with uuid: " + uuid));

        return judge.getResultForChallengeUser(
                challengeInfo.getTotalScoreUser1(),
                challengeInfo.getTotalScoreUser2(),
                challengeInfo.getTimeToSolveUser1(),
                challengeInfo.getTimeToSolveUser2()) == Result.USER_2_WIN;
    }

    void determineResultAndSendEmail(ChallengeResult challengeResult) throws MessagingException {
        var challengeInfo = getChallenge(challengeResult);

        if (challengeAgainstUserWithoutEmail(challengeResult)) {
            if (judge.getResultForChallengeUser(
                    challengeInfo.getTotalScoreUser1(),
                    challengeInfo.getTotalScoreUser2(),
                    challengeInfo.getTimeToSolveUser1(),
                    challengeInfo.getTimeToSolveUser2()) == Result.USER_1_WIN) {
                challengeInfo.setResult(Result.USER_1_WIN);
                challengeRepository.save(challengeInfo);
                sendEmailWon(challengeResult, challengeInfo.getEmailUser1());
            } else {
                challengeInfo.setResult(Result.USER_2_WIN);
                challengeRepository.save(challengeInfo);
                sendEmailLost(challengeResult, challengeInfo.getEmailUser1());
            }
        } else {
            if (judge.getResultForChallengeUser(
                    challengeInfo.getTotalScoreUser1(),
                    challengeInfo.getTotalScoreUser2(),
                    challengeInfo.getTimeToSolveUser1(),
                    challengeInfo.getTimeToSolveUser2()) == Result.USER_1_WIN) {
                challengeInfo.setResult(Result.USER_1_WIN);
                challengeRepository.save(challengeInfo);
                sendEmailWon(challengeResult, challengeInfo.getEmailUser1());
                sendEmailLost(challengeResult, challengeInfo.getEmailUser2());
            } else {
                challengeInfo.setResult(Result.USER_2_WIN);
                challengeRepository.save(challengeInfo);
                sendEmailLost(challengeResult, challengeInfo.getEmailUser1());
                sendEmailWon(challengeResult, challengeInfo.getEmailUser2());
            }
        }
    }

    boolean challengeAgainstUserWithoutEmail(ChallengeResult challengeResult) {
        var challenge = getChallenge(challengeResult);
        return challenge.getEmailUser2() == null;
    }

    void sendEmailWon(ChallengeResult challengeResult, String user) throws MessagingException {
        String subject = "Challenge: " + challengeResult.getUuid() + " result";
        String won = "Congratulations you've won!\n Here is a link: " + "<a href=" +
                getChallengeDetailsUrl(challengeResult.getUuid()) + ">" + "Result" + "</a>";
        emailService.sendEmail(user, subject, won);
    }

    void sendEmailLost(ChallengeResult challengeResult, String user) throws MessagingException {
        String subject = "Challenge: " + challengeResult.getUuid() + " result";
        String lost = "Unfortunately you've lost :(\n Here is a link: " + "<a href=" +
                getChallengeDetailsUrl(challengeResult.getUuid()) + ">" + "Result" + "</a>";
        emailService.sendEmail(user, subject, lost);
    }

    boolean checkIfQuizHasBeenCompletedByUser1(UUID uuid) {
        var challenges = challengeRepository.findById(uuid).get();
        return challenges.getEmailUser1() != null;
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

    private Challenge getChallenge(ChallengeResult challengeResult) {
        var challengeInfoData = challengeRepository.findById(challengeResult.getUuid());
        return challengeInfoData.orElseThrow(() -> new NotFoundException("Couldn't find the challenge with uuid: " + challengeResult.getUuid()));
    }

    List<Challenge> getAllChallenges() {
        return challengeRepository.findAll();
    }

    Challenge getSingleChallenge(UUID uuid) {
        return challengeRepository.findById(uuid).get();
    }

    String getChallengeDetailsUrl(UUID uuid) {
        return server + "challengeDetails.html?uuid=" + uuid;
    }

    void sendChallengeEmail(String receiver, String sender, UUID uuid) throws MessagingException {
        emailService.sendEmail(receiver, "Challenge",
                "You have been challenged by: " + sender +
                        "<a href=" + server + "challenge.html?uuid=" + uuid + ">" + " " + "Challenge" + "</a>");
    }
}
