package pl.piekoszek.gorskimatches.equation;

import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Set;

@Component
public record SolvableEquations(EquationGenerator equationGenerator) {

    public boolean checkForCorrectAnswer(String quiz, String solution) {
        Map<String, Set<String>> allQuizzesAndSolutions = equationGenerator.getAllQuizzesAndSolutions();
        var correctSolution = allQuizzesAndSolutions.get(quiz);
        return correctSolution.contains(solution);
    }
}
