package pl.piekoszek.gorskimatches.equation;

import org.springframework.web.bind.annotation.*;
import pl.piekoszek.gorskimatches.validation.PatternMatches;
import javax.validation.Valid;

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
    boolean checkAnswer(@Valid @RequestBody Equation equation) {
        return solvableEquations.checkForCorrectAnswer(equation.quiz, patternMatches.removeSpaces(equation.answer));
    }
}
