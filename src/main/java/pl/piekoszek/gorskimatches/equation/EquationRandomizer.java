package pl.piekoszek.gorskimatches.equation;

import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class EquationRandomizer {

    private final QuizzesGenerator quizzesGenerator;

    public EquationRandomizer(QuizzesGenerator quizzesGenerator) {
        this.quizzesGenerator = quizzesGenerator;
    }

    public String randomEquation() {
        var quizzes = new ArrayList<>(quizzesGenerator.getAllSolutionsByQuiz().keySet());
        return quizzes.get(new Random().nextInt(quizzes.size()));
    }

    public Set<String> equationsForChallenge() {
        var quizzes = new ArrayList<>(quizzesGenerator.getAllSolutionsByQuiz().keySet());
        Set<String> fiveQuizzesForChallenge = new HashSet<>();
        while (fiveQuizzesForChallenge.size() < 5) {
            fiveQuizzesForChallenge.add(quizzes.get(new Random().nextInt(quizzes.size())));
        }
        return fiveQuizzesForChallenge;
    }
}