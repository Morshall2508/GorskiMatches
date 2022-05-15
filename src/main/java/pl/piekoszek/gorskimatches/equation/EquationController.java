package pl.piekoszek.gorskimatches.equation;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/equation")
class EquationController {

    private final EquationRandomizer equationRandomizer;

    EquationController(EquationRandomizer equationRandomizer, SolvableEquations solvableEquations) {
        this.equationRandomizer = equationRandomizer;
        this.solvableEquations = solvableEquations;
    }

    @GetMapping("random")
    String fetchString() {
        return equationRandomizer.randomEquation();
    }

    private final SolvableEquations solvableEquations;

    @PostMapping("random/solution")
    EquationInfo equationInfo(@RequestBody EquationInfo equationInfo) {
        return new EquationInfo(
                equationInfo.quiz = equationRandomizer.randomEquation(),
                equationInfo.solution = String.valueOf(solvableEquations.checkForCorrectAnswer(equationInfo.quiz, equationInfo.solution)));

    }
}
