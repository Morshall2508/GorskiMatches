package pl.piekoszek.gorskimatches.challange;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.piekoszek.gorskimatches.equation.EquationRandomizer;

import java.util.List;

@RestController
@RequestMapping("api/challenge")
public class ChallengeController {

    private final EquationRandomizer equationRandomizer;

    private final TimerCountdown timerCountdown;

    public ChallengeController(EquationRandomizer equationRandomizer, TimerCountdown timerCountdown) {
        this.equationRandomizer = equationRandomizer;
        this.timerCountdown = timerCountdown;
    }

    @GetMapping
    List fetchEquations () {
        return equationRandomizer.equationsForChallenge();
    }

    @GetMapping("countdown")
    void getTimer () {
        timerCountdown.runnable.run();
    }

}
