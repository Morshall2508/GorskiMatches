package pl.piekoszek.gorskimatches.equation;

import org.springframework.web.bind.annotation.*;
import pl.piekoszek.gorskimatches.validation.StringValidator;

import javax.validation.Valid;

@RestController
@RequestMapping("api/equation")
class EquationController {

    private final EquationRandomizer equationRandomizer;

    private final StringValidator stringValidator;

    private final QuizAnswerChecker solvableEquations;

    EquationController(EquationRandomizer equationRandomizer, QuizAnswerChecker solvableEquations, StringValidator stringValidator) {
        this.equationRandomizer = equationRandomizer;
        this.solvableEquations = solvableEquations;
        this.stringValidator = stringValidator;
    }

    @GetMapping("random")
    String fetchString() {
        return equationRandomizer.randomEquation();
    }

    @PostMapping("solution")
    boolean checkAnswer(@Valid @RequestBody Equation equation) {
        return solvableEquations.checkForCorrectAnswer(equation.quiz, stringValidator.removeSpaces(equation.answer));
    }
}
