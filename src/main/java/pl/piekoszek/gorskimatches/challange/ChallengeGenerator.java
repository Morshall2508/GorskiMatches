package pl.piekoszek.gorskimatches.challange;

import org.springframework.stereotype.Component;
import pl.piekoszek.gorskimatches.equation.EquationRandomizer;

import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class ChallengeGenerator {

    private final EquationRandomizer equationRandomizer;

    private final GenerateUUID generateUUID;

    private final ChallengeRepository challengeRepository;

    private final TimeService timeService;

    public ChallengeGenerator(EquationRandomizer equationRandomizer, GenerateUUID generateUUID, ChallengeRepository challengeRepository, TimeService timeService) {
        this.equationRandomizer = equationRandomizer;
        this.generateUUID = generateUUID;
        this.challengeRepository = challengeRepository;
        this.timeService = timeService;
    }

    UUID createChallenge() {
        var challenge = new Challenge();
        challenge.setChallengeQuizzes(
                equationRandomizer.equationsForChallenge().stream()
                        .map(quiz -> new ChallengeQuiz(challenge, quiz))
                        .collect(Collectors.toList()));
        challenge.setUuid(generateUUID.generateUUID());
        challenge.setCreationTime(timeService.millisSinceEpoch());
        challengeRepository.save(challenge);
        return challenge.getUuid();
    }
}
