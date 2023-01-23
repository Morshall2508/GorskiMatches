package pl.piekoszek.gorskimatches.challange;

import org.springframework.web.bind.annotation.*;
import pl.piekoszek.gorskimatches.config.authorization.Email;

import javax.mail.MessagingException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/challenge")
class ChallengeController {

    private final ChallengeService challengeService;

    public ChallengeController(ChallengeService challengeService) {
        this.challengeService = challengeService;
    }

    @GetMapping("generate")
    UUID createChallenge() {
        return challengeService.createChallenge();
    }

    @PostMapping("score")
    void saveRegisteredUserAndGetResults(@Email(required = false) String email, @RequestBody ChallengeResult challengeResult) throws MessagingException {
        if (email != null) {
            challengeService.saveRegisteredUserResult(challengeResult, email);
            return;
        }
        challengeService.saveNonRegisteredUserResult(challengeResult);
        challengeService.resultForRegisteredUser(challengeResult);
    }

    @GetMapping("resultForNonRegistered/{uuid}")
    String getResultForNonRegisteredUser(@PathVariable("uuid") UUID uuid) {
        return challengeService.resultForNonRegisteredUser(uuid);
    }

    @GetMapping("quizzes/{uuid}")
    List<String> fetchQuizzes(@PathVariable("uuid") UUID uuid) {
        return challengeService.getQuizzes(uuid);
    }

    @PostMapping("challengeQuizzesAndAnswers/{uuid}")
    void saveChallengeData(@Email(required = false) String email, @PathVariable("uuid") UUID uuid, @RequestBody ChallengeScoreAndAnswers challengeScoreAndAnswers) {
        if (email != null) {
            challengeService.calculateAndSaveScoreForUser1(uuid, challengeScoreAndAnswers);
            return;
        }
        challengeService.calculateAndSaveScoreForUser2(uuid, challengeScoreAndAnswers);
    }

    @GetMapping("challenges")
    List<Challenge> returnsChallenges() {
        return challengeService.getChallenges();
    }

    @GetMapping("{uuid}")
    Challenge getChallenge(@PathVariable("uuid") UUID uuid){
        return challengeService.getChallenge(uuid);
    }
}
