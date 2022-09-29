package pl.piekoszek.gorskimatches.equation;

import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/equation")
class EquationController {

    private final EquationRandomizer equationRandomizer;

    private final QuizAnswerChecker solvableEquations;

    EquationController(EquationRandomizer equationRandomizer, QuizAnswerChecker solvableEquations) {
        this.equationRandomizer = equationRandomizer;
        this.solvableEquations = solvableEquations;
    }

    @GetMapping("random")
    String fetchString() {
        return equationRandomizer.randomEquation();
    }

    @PostMapping("solution")
    boolean checkAnswer(@Valid @RequestBody Equation equation) {
        return solvableEquations.checkForCorrectAnswer(equation.quiz, equation.answer);
    }
}
