package pl.piekoszek.gorskimatches.equation;

import org.springframework.stereotype.Component;

@Component
public class QuizAnswerChecker {

    private final QuizzesGenerator equationGenerator;

    public QuizAnswerChecker(QuizzesGenerator equationGenerator) {
        this.equationGenerator = equationGenerator;
    }

    public boolean checkForCorrectAnswer(String quiz, String solution) {
        return equationGenerator.getAllSolutionsByQuiz().get(quiz).contains(solution);
    }
}
