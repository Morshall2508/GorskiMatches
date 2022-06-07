package pl.piekoszek.gorskimatches.equation;

import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class EquationRandomizer {

    private final QuizzesGenerator equationGenerator;

    public EquationRandomizer(QuizzesGenerator equationGenerator) {
        this.equationGenerator = equationGenerator;
    }

    public String randomEquation() {
        var quizzes = new ArrayList<>(equationGenerator.getAllSolutionsByQuiz().keySet());
        return quizzes.get(new Random().nextInt(quizzes.size()));
    }
}