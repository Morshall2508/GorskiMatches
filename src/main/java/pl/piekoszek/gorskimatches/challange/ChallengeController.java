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

    private final ChallengeGenerator challengeGenerator;
    public ChallengeController(ChallengeService challengeService, ChallengeGenerator challengeGenerator) {
        this.challengeService = challengeService;
        this.challengeGenerator = challengeGenerator;
    }

    @GetMapping("generate")
    UUID createChallenge() {
        return challengeGenerator.createChallenge();
    }

    @PostMapping("save")
    void saveUsersScore(@Email(required = false) String email, @RequestBody ChallengeResult challengeResult) throws MessagingException {
        if (email != null) {
            challengeService.saveUserWithEmailAndSendEmail(challengeResult, email);
            return;
        }
        challengeService.saveUserWithoutEmailAndSendEmail(challengeResult);
    }

    @GetMapping("quizzes/{uuid}")
    List<String> getQuizzes(@PathVariable("uuid") UUID uuid) {
        return challengeService.getQuizzes(uuid);
    }

    @GetMapping("challenges")
    List<Challenge> returnChallenges() {
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
