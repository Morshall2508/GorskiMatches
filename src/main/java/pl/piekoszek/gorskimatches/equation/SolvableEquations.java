package pl.piekoszek.gorskimatches.equation;

import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Set;

@Component
public class SolvableEquations {

    private final EquationGenerator equationGenerator;

    public SolvableEquations(EquationGenerator equationGenerator) {
        this.equationGenerator = equationGenerator;
    }

    public boolean checkForCorrectAnswer(String quiz, String solution) {
        return equationGenerator.getAllSolutionsByQuiz().get(quiz).contains(solution);
    }
}
