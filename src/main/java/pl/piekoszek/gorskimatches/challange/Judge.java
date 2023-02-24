package pl.piekoszek.gorskimatches.challange;

import org.springframework.stereotype.Component;
import pl.piekoszek.gorskimatches.equation.QuizAnswerChecker;

@Component
class Judge {

    private final QuizAnswerChecker quizAnswerChecker;

    public Judge(QuizAnswerChecker quizAnswerChecker) {
        this.quizAnswerChecker = quizAnswerChecker;
    }

    Result getResultForChallengeUser(int scoreUser1, int scoreUser2, float timeUser1, float timeUser2) {
        if (scoreUser1 != scoreUser2) {
            if (scoreUser1 > scoreUser2) {
                return Result.USER_1_WIN;
            } else {
                return Result.USER_2_WIN;
            }
        } else {
            if (timeUser1 < timeUser2) {
                return Result.USER_1_WIN;
            } else {
                return Result.USER_2_WIN;
            }
        }
    }

    boolean verifyAnswerToQuiz(String quiz, String answer) {
        return quizAnswerChecker.checkForCorrectAnswer(quiz, answer);
    }
}
