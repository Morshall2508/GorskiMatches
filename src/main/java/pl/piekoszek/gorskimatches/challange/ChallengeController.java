package pl.piekoszek.gorskimatches.challange;

import org.springframework.web.bind.annotation.*;
import pl.piekoszek.gorskimatches.token.Email;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/challenge")
public class ChallengeController {

    private ChallengeRepository challengeRepository;

    private ChallengeService challengeService;

    public ChallengeController(ChallengeRepository challengeRepository, ChallengeService challengeService) {
        this.challengeRepository = challengeRepository;
        this.challengeService = challengeService;
    }

    @GetMapping("generate")
    UUID createChallenge() {
        return challengeService.createChallenge();
    }

    @PostMapping("score")
    void saveAndGetResultForRegisteredUser(@Email(required = false) String email, @RequestBody ChallengeResult challengeResult) {
        var challengeInfoData = challengeRepository.findById(challengeResult.getUuid());
        var challengeInfo = challengeInfoData.get();

        if (email != null) {
            challengeInfo.setEmail(email);
            challengeInfo.setRegisteredUserScore(challengeResult.getScore());
            challengeInfo.setRegisteredUserTimeSeconds(challengeResult.getTime());
            challengeRepository.save(challengeInfo);
            return;
        }
        challengeInfo.setNonRegisteredUserScore(challengeResult.getScore());
        challengeInfo.setNonRegisteredUserTimeSeconds(challengeResult.getTime());
        challengeRepository.save(challengeInfo);

        challengeService.resultForRegisteredUser(challengeInfo,
                challengeInfo.getRegisteredUserTimeSeconds(),
                challengeInfo.getNonRegisteredUserTimeSeconds(),
                challengeInfo.getRegisteredUserScore(),
                challengeInfo.getNonRegisteredUserScore());
    }

    @GetMapping("resultForNonRegistered/{uuid}")
    String getResultForNonRegisteredUser(@PathVariable("uuid") Challenge challenge) {
        var challengeInfoData = challengeRepository.findById(challenge.getUuid());
        var challengeInfo = challengeInfoData.get();
        return challengeService.resultForNonRegisteredUser(challengeInfo.getRegisteredUserTimeSeconds(),
                challengeInfo.getNonRegisteredUserTimeSeconds(),
                challengeInfo.getRegisteredUserScore(),
                challengeInfo.getNonRegisteredUserScore());

    }

    @GetMapping("quizzes/{uuid}")
    List<String> fetchQuizzes(@PathVariable("uuid") UUID uuid) {
        return challengeRepository.findById(uuid).get().getChallengeQuizzes().stream()
                .map(ChallengeQuiz::getQuiz)
                .collect(Collectors.toList());
    }


}
