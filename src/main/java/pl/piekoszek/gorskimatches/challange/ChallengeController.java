package pl.piekoszek.gorskimatches.challange;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.piekoszek.gorskimatches.equation.EquationRandomizer;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/challenge")
public class ChallengeController {

    private final EquationRandomizer equationRandomizer;

    private final GenerateUUID generateUUID;

    private final TimerCountdown timerCountdown;

    public ChallengeController(EquationRandomizer equationRandomizer, GenerateUUID generateUUID, TimerCountdown timerCountdown) {
        this.equationRandomizer = equationRandomizer;
        this.generateUUID = generateUUID;
        this.timerCountdown = timerCountdown;
    }

    @GetMapping("generate")
    UUID getUUID () {
        return generateUUID.generateUUID();
    }

    @GetMapping("equations")
    ArrayList<String> fetchEquations () {
        return equationRandomizer.equationsForChallenge();
    }

    @GetMapping("countdown")
    void getTimer () {
        timerCountdown.runnable.run();
    }

}
