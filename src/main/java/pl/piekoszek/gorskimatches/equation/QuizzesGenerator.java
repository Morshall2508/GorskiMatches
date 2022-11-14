package pl.piekoszek.gorskimatches.equation;

import org.springframework.stereotype.Component;

import java.util.*;

@Component
class QuizzesGenerator {

    Map<String, Set<String>> solutionsByQuiz = new HashMap<>();

    QuizzesGenerator(SolutionToQuizzesMapper solutionToQuizzesMapper, EquationGenerator equationGenerator) {
        var mathematicallyCorrectEquations = equationGenerator.mathematicallyCorrectEquations();
        for (String correctEquation : mathematicallyCorrectEquations) {
            solutionToQuizzesMapper.insideSingleNumber(correctEquation).forEach((quiz, solution) -> {
                solutionsByQuiz.putIfAbsent(quiz, new HashSet<>());
                solutionsByQuiz.get(quiz).addAll(solution);
            });
        }
        for (String correctEquation : mathematicallyCorrectEquations) {
            solutionToQuizzesMapper.insideEquation(correctEquation).forEach((quiz, solution) -> {
                solutionsByQuiz.putIfAbsent(quiz, new HashSet<>());
                solutionsByQuiz.get(quiz).addAll(solution);
            });
        }
    }

    Map<String, Set<String>> getAllSolutionsByQuiz() {
        return solutionsByQuiz;
    }
}
