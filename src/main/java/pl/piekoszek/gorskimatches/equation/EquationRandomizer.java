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

    public ArrayList<String> equationsForChallenge() {
        var quizzes = new ArrayList<>(equationGenerator.getAllSolutionsByQuiz().keySet());
        ArrayList<String> fiveQuizzesForChallenge = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            fiveQuizzesForChallenge.add(quizzes.get(new Random().nextInt(quizzes.size())));
        }
        return fiveQuizzesForChallenge;
    }
}