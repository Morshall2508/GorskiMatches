package pl.piekoszek.gorskimatches.equation;

import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class EquationGenerator {

    public Map<String, Set<String>> solutionsByQuiz = new HashMap<>();

    public EquationGenerator(SolutionToQuizzesMapper solutionToQuizzesMapper, MathematicallyCorrectEquations mathematicallyCorrectEquations) {

        for (Object correctEquation : mathematicallyCorrectEquations.mathematicallyCorrectEquationsGenerator()) {
            solutionToQuizzesMapper.insideSingleMatch((String) correctEquation).forEach((quiz, solution) -> {
                solutionsByQuiz.putIfAbsent(quiz, new HashSet<>());
                solutionsByQuiz.get(quiz).addAll(solution);
            });
        }
    }

    public Map<String, Set<String>> getAllSolutionsByQuiz() {
        return solutionsByQuiz;
    }
}
