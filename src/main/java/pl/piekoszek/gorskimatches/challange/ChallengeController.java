package pl.piekoszek.gorskimatches.challange;

import org.springframework.web.bind.annotation.*;
import pl.piekoszek.gorskimatches.token.Email;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/challenge")
public class ChallengeController {

    private ChallengeService challengeService;

    public ChallengeController(ChallengeService challengeService) {
        this.challengeService = challengeService;
    }

    @GetMapping("generate")
    UUID createChallenge() {
        return challengeService.createChallenge();
    }

    @PostMapping("score")
    void saveRegisteredUserAndGetResults(@Email(required = false) String email, @RequestBody ChallengeResult challengeResult) {

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

    @PostMapping("challengeQuizzesData/{uuid}")
    void saveChallengeData(@Email(required = false) String email, @PathVariable("uuid") UUID uuid, @RequestBody ChallengeHistory challengeHistory) {
        if (email != null) {
            challengeService.saveUser1ScoreAndAnswers(uuid, challengeHistory);
            return;
        }
        challengeService.saveUser2ScoreAndAnswers(uuid, challengeHistory);
    }

    @GetMapping("challenges")
    List<Challenge> returnsChallenges(){
        return challengeService.getChallenges();
    }
}
