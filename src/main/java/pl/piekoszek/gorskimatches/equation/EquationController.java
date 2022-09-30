package pl.piekoszek.gorskimatches.equation;

import org.springframework.web.bind.annotation.*;
import pl.piekoszek.gorskimatches.validation.StringEditor;

import javax.validation.Valid;

@RestController
@RequestMapping("api/equation")
class EquationController {

    private final EquationRandomizer equationRandomizer;

    private final StringEditor stringEditor;

    private final QuizAnswerChecker solvableEquations;

    EquationController(EquationRandomizer equationRandomizer, QuizAnswerChecker solvableEquations, StringEditor stringEditor) {
        this.equationRandomizer = equationRandomizer;
        this.solvableEquations = solvableEquations;
        this.stringEditor = stringEditor;
    }

    @GetMapping("random")
    String fetchString() {
        return equationRandomizer.randomEquation();
    }

    @PostMapping("solution")
    boolean checkAnswer(@Valid @RequestBody Equation equation) {
        return solvableEquations.checkForCorrectAnswer(equation.quiz, stringEditor.removeSpaces(equation.answer));
    }
}
