package pl.piekoszek.gorskimatches.equation;

import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class QuizzesGenerator {

    public Map<String, Set<String>> solutionsByQuiz = new HashMap<>();

    public QuizzesGenerator(SolutionToQuizzesMapper solutionToQuizzesMapper, EquationGenerator mathematicallyCorrectEquations) {

        for (String correctEquation : mathematicallyCorrectEquations.mathematicallyCorrectEquations()) {
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
