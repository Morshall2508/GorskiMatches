package pl.piekoszek.gorskimatches.challange;

import org.springframework.jdbc.core.SqlReturnType;
import org.springframework.web.bind.annotation.*;
import pl.piekoszek.gorskimatches.equation.EquationRandomizer;
import pl.piekoszek.gorskimatches.token.Email;
import pl.piekoszek.gorskimatches.token.EmailService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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

    @PostMapping("data")
    void createData(@RequestBody Challenge challenge) {
        challengeRepository.save(challenge);
    }

    @PostMapping("score")
    void getScore(@RequestBody Challenge challenge) {
        if (challenge.getEmail() != null) {
            challengeRepository.save(challenge);
        }
        var nonRegisterScore = challengeRepository.findById(challenge.getUuid());
        var nonRegister = nonRegisterScore.get();
        nonRegister.setNonRegisteredUserScore(challenge.getNonRegisteredUserScore());
        nonRegister.setNonRegisteredUserTime(challenge.getNonRegisteredUserTime());
        challengeRepository.save(nonRegister);
    }

    @PostMapping("info")
    void getInfo(@RequestBody Challenge challenge) {
        challengeRepository.save(challenge);
    }

    @GetMapping("quizzes/{uuid}")
    List<String> fetchQuizzes(@PathVariable("uuid") UUID uuid) {
        return challengeRepository.findById(uuid).get().getChallengeQuizzes().stream()
                .map(ChallengeQuiz::getQuiz)
                .collect(Collectors.toList());
    }

    @GetMapping("resultForNonRegistered")
    String getResultForNonRegisteredUser(Challenge challenge) {
        var challengeData = challengeRepository.findById(challenge.getUuid());
        var challengeInfo = challengeData.get();
        if (challengeInfo.getRegisteredUserScore() != challengeInfo.getRegisteredUserScore()) {
            if (challengeInfo.getNonRegisteredUserScore() > challengeInfo.getRegisteredUserScore()) {
                return "Congratulations you've won!";
            }
            return "Unfortunately you've lost :(";
        }
        return null;
    }

    @GetMapping("resultForRegisteredUser")
    void getResultForRegisteredUser(Challenge challenge) {
        var challengeData = challengeRepository.findById(challenge.getUuid());
        var challengeInfo = challengeData.get();
        if (challengeInfo.getRegisteredUserScore() != challengeInfo.getRegisteredUserScore()) {
            if (challengeInfo.getRegisteredUserScore() > challengeInfo.getNonRegisteredUserScore()) {
                emailService.sendChallengeWon(challenge.getEmail());
            }
            emailService.sendChallengeLos(challenge.getEmail());
        }
    }
}
