package pl.piekoszek.gorskimatches.equation;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pl.piekoszek.gorskimatches.validation.PatternMatches;

@RestController
@RequestMapping("api/equation")
class EquationController {

    private final EquationRandomizer equationRandomizer;
    private final PatternMatches patternMatches;

    EquationController(EquationRandomizer equationRandomizer, QuizAnswerChecker solvableEquations, PatternMatches patternMatches) {
        this.equationRandomizer = equationRandomizer;
        this.solvableEquations = solvableEquations;
        this.patternMatches = patternMatches;
    }

    @GetMapping("random")
    String fetchString() {
        return equationRandomizer.randomEquation();
    }
    private final QuizAnswerChecker solvableEquations;

    @PostMapping("solution")
    boolean checkAnswer(@RequestBody Equation equation) {
        return solvableEquations.checkForCorrectAnswer(equation.quiz, patternMatches.removeSpaces(equation.answer));
    }
}
