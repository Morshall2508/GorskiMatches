package pl.piekoszek.gorskimatches.equation;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Component
public class SolvableEquations {

    private final EquationGenerator equationGenerator;

    public SolvableEquations(EquationGenerator equationGenerator) {
        this.equationGenerator = equationGenerator;

    }

    public boolean checkForCorrectAnswer(String quiz, String solution) {
        Map<String, Set<String>> allQuizzesAndSolutions = equationGenerator.getAllQuizzesAndSolutions();
        var correctSolution = allQuizzesAndSolutions.get(quiz);
        return correctSolution.equals(solution);
    }
}

