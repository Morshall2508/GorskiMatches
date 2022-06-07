package pl.piekoszek.gorskimatches.equation;

import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class QuizzGenerator {

    public Map<String, Set<String>> solutionsByQuiz = new HashMap<>();

    public QuizzGenerator(SolutionToQuizzesMapper solutionToQuizzesMapper, EquationGenerator mathematicallyCorrectEquations) {

        for (String correctEquation : mathematicallyCorrectEquations.mathematicallyCorrectEquationsGenerator()) {
            solutionToQuizzesMapper.insideSingleMatch(correctEquation).forEach((quiz, solution) -> {
                solutionsByQuiz.putIfAbsent(quiz, new HashSet<>());
                solutionsByQuiz.get(quiz).addAll(solution);
            });
        }
    }

    public Map<String, Set<String>> getAllSolutionsByQuiz() {
        return solutionsByQuiz;
    }
}
