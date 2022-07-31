package pl.piekoszek.gorskimatches.challange;

import org.springframework.web.bind.annotation.*;
import pl.piekoszek.gorskimatches.equation.EquationRandomizer;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("api/challenge")
public class ChallengeController {

    private final EquationRandomizer equationRandomizer;

    private final GenerateUUID generateUUID;

    private ChallengeRepository challengeRepository;

    public ChallengeController(EquationRandomizer equationRandomizer, GenerateUUID generateUUID, ChallengeRepository challengeRepository) {
        this.equationRandomizer = equationRandomizer;
        this.generateUUID = generateUUID;
        this.challengeRepository = challengeRepository;
    }

    @GetMapping("generate")
    UUID getUUID() {
        return generateUUID.generateUUID();
    }

    @GetMapping("equations")
    ArrayList<String> fetchEquations() {
        return equationRandomizer.equationsForChallenge();
    }

    @PostMapping("data")
    void createData(@RequestBody Challenge challenge) {
        challengeRepository.save(challenge);
    }

    @PostMapping("score")
    void getScore(@RequestBody Challenge challenge) {
        if (challenge.getEmail() != null) {
            challengeRepository.save(challenge);
        } var nonRegisterScore = challengeRepository.findById(challenge.getUuid());
        nonRegisterScore.setNonRegisteredUserScore(challenge.getNonRegisteredUserScore());
        challengeRepository.save(nonRegisterScore);
        }
    }
