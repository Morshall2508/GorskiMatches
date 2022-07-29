package pl.piekoszek.gorskimatches.challange;

import org.springframework.web.bind.annotation.*;
import pl.piekoszek.gorskimatches.equation.EquationRandomizer;
import pl.piekoszek.gorskimatches.token.AccountInfo;
import pl.piekoszek.gorskimatches.token.Email;

import java.util.ArrayList;
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

    @PostMapping("getChallenge")
    void createChallenge (@Email String email, @RequestBody Challenge challenge){
            challengeRepository.save(challenge);
    }
}
