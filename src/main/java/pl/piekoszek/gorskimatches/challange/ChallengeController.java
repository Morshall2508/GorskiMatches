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
    void saveUserSScoreAndGetResults(@Email(required = false) String email, @RequestBody ChallengeResult challengeResult) throws MessagingException {
        if (email != null) {
            challengeService.saveUserWithEmailAndReturnResult(challengeResult, email);
            return;
        }
        challengeService.saveUserWithoutEmailAndReturnResult(challengeResult);
    }

    @GetMapping("resultForNonRegistered/{uuid}")
    String getResultForNonRegisteredUser(@PathVariable("uuid") UUID uuid) {
        return challengeService.resultForUserWithoutEmail(uuid);
    }

    @GetMapping("quizzes/{uuid}")
    List<String> getQuizzes(@PathVariable("uuid") UUID uuid) {
        return challengeService.getQuizzes(uuid);
    }

    @GetMapping("challenges")
    List<Challenge> returnsChallenges() {
        return challengeService.getAllChallenges();
    }

    @GetMapping("{uuid}")
    Challenge getChallenge(@PathVariable("uuid") UUID uuid) {
        return challengeService.getSingleChallenge(uuid);
    }

    @PostMapping("sendChallenge")
    void sendChallenge(@RequestBody ChallengeRequest challengeRequest) throws MessagingException {
        challengeService.sendChallengeEmail(challengeRequest.getReceiver(), challengeRequest.getSender(), challengeRequest.getUuid());
    }
}
