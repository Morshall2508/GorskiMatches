package pl.piekoszek.gorskimatches.challange;

import org.springframework.web.bind.annotation.*;
import pl.piekoszek.gorskimatches.equation.EquationRandomizer;
import pl.piekoszek.gorskimatches.token.EmailService;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/challenge")
public class ChallengeController {

    private final EquationRandomizer equationRandomizer;

    private final GenerateUUID generateUUID;

    private EmailService emailService;

    private ChallengeRepository challengeRepository;

    public ChallengeController(EquationRandomizer equationRandomizer, GenerateUUID generateUUID, ChallengeRepository challengeRepository, EmailService emailService) {
        this.equationRandomizer = equationRandomizer;
        this.generateUUID = generateUUID;
        this.challengeRepository = challengeRepository;
        this.emailService = emailService;
    }

    @GetMapping("generate")
    UUID getUUID() {
        var challenge = new Challenge();
        challenge.setChallengeQuizzes(
                equationRandomizer.equationsForChallenge().stream()
                        .map(quiz -> new ChallengeQuiz(challenge, quiz))
                        .collect(Collectors.toList()));
        challenge.setUuid(generateUUID.generateUUID());
        challengeRepository.save(challenge);
        return challenge.getUuid();
    }

    @PostMapping("score")
    void getScore(@RequestBody Challenge challenge) {
        var challengeInfoData = challengeRepository.findById(challenge.getUuid());
        var challengeInfo = challengeInfoData.get();

        if (challenge.getEmail() != null) {
            challengeInfo.setEmail(challenge.getEmail());
            challengeInfo.setRegisteredUserScore(challenge.getRegisteredUserScore());
            challengeInfo.setRegisteredUserTime(challenge.getRegisteredUserTime());
            challengeRepository.save(challengeInfo);

        } else {
            challengeInfo.setNonRegisteredUserScore(challenge.getNonRegisteredUserScore());
            challengeInfo.setNonRegisteredUserTime(challenge.getNonRegisteredUserTime());
            challengeRepository.save(challengeInfo);

            if (challengeInfo.getNonRegisteredUserTime() != challengeInfo.getRegisteredUserTime()) {
                if (challengeInfo.getRegisteredUserScore() > challengeInfo.getNonRegisteredUserScore()) {
                    emailService.sendChallengeWon(challengeInfo.getEmail());
                } else {
                    emailService.sendChallengeLost(challengeInfo.getEmail());
                }
            }
            if (challengeInfo.getRegisteredUserScore() == challengeInfo.getNonRegisteredUserScore()) {
                if (challengeInfo.getRegisteredUserTime() < challengeInfo.getNonRegisteredUserTime()) {
                    emailService.sendChallengeWon(challengeInfo.getEmail());
                } else {
                    emailService.sendChallengeLost(challengeInfo.getEmail());
                }
            }
        }
    }

    @GetMapping("quizzes/{uuid}")
    List<String> fetchQuizzes(@PathVariable("uuid") UUID uuid) {
        return challengeRepository.findById(uuid).get().getChallengeQuizzes().stream()
                .map(ChallengeQuiz::getQuiz)
                .collect(Collectors.toList());
    }

    @GetMapping("resultForNonRegistered/{uuid}")
    String getResultForNonRegisteredUser(@PathVariable("uuid") Challenge challenge) {
        var challengeInfoData = challengeRepository.findById(challenge.getUuid());
        var challengeInfo = challengeInfoData.get();
        if (challengeInfo.getRegisteredUserScore() != challengeInfo.getNonRegisteredUserScore()) {
            if (challengeInfo.getNonRegisteredUserScore() > challengeInfo.getRegisteredUserScore()) {
                return "Congratulations you've won!";
            }
            return "Unfortunately you've lost :(";
        }
        if (challengeInfo.getRegisteredUserScore() == challengeInfo.getNonRegisteredUserScore()) {
            if (challengeInfo.getNonRegisteredUserTime() < challengeInfo.getRegisteredUserTime()) {
                return "Congratulations you've won!";
            }
        }
        return "Unfortunately you've lost :(";
    }
}
