package pl.piekoszek.gorskimatches.equation;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/equation")
class EquationController {

    private final EquationRandomizer equationRandomizer;

    EquationController(EquationRandomizer equationRandomizer, QuizAnswerChecker solvableEquations) {
        this.equationRandomizer = equationRandomizer;
        this.solvableEquations = solvableEquations;
    }

    @GetMapping("random")
    String fetchString() {
        return equationRandomizer.randomEquation();
    }

    private final QuizAnswerChecker solvableEquations;

    @PostMapping("solution")
    boolean checkAnswer(@RequestBody Equation equation) {
        return solvableEquations.checkForCorrectAnswer(equation.quiz, equation.answer);
    }
}
