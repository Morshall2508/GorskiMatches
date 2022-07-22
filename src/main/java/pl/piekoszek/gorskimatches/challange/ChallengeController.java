package pl.piekoszek.gorskimatches.challange;

import org.springframework.web.bind.annotation.*;
import pl.piekoszek.gorskimatches.equation.EquationRandomizer;

import java.util.ArrayList;
import java.util.UUID;

@RestController
@RequestMapping("api/challenge")
public class ChallengeController {

    private final EquationRandomizer equationRandomizer;

    private final GenerateUUID generateUUID;

    public ChallengeController(EquationRandomizer equationRandomizer, GenerateUUID generateUUID) {
        this.equationRandomizer = equationRandomizer;
        this.generateUUID = generateUUID;
    }

    @GetMapping("generate")
    UUID getUUID() {
        return generateUUID.generateUUID();
    }

    @GetMapping("equations")
    ArrayList<String> fetchEquations() {
        return equationRandomizer.equationsForChallenge();
    }

    @PostMapping("score")
    void fetchQuizResultAndScore (@RequestBody ScoreInfo scoreInfo){

    }
}
