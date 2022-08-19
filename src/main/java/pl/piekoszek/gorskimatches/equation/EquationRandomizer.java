package pl.piekoszek.gorskimatches.equation;

import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class EquationRandomizer {

    private final QuizzesGenerator quizzesGenerator;

    public EquationRandomizer(QuizzesGenerator quizzesGenerator) {
        this.quizzesGenerator = quizzesGenerator;
    }

    List<String> quizzes = new ArrayList<>();
    public String randomEquation() {
        quizzes.addAll(quizzesGenerator.getAllSolutionsByQuiz().keySet());
        return quizzes.get(new Random().nextInt(quizzes.size()));
    }

    public Set<String> equationsForChallenge() {
        Set<String> fiveQuizzesForChallenge = new HashSet<>();
        while (fiveQuizzesForChallenge.size() < 5) {
            fiveQuizzesForChallenge.add(randomEquation());
        }
        return fiveQuizzesForChallenge;
    }
}