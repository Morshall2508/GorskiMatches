package pl.piekoszek.gorskimatches.facebook;

import org.springframework.stereotype.Component;
import pl.piekoszek.gorskimatches.equation.EquationRandomizer;
import pl.piekoszek.gorskimatches.equation.QuizAnswerChecker;
import pl.piekoszek.gorskimatches.repository.FacebookRepository;

@Component
public class FacebookQuiz {

    private final QuizAnswerChecker answerChecker;

    private final EquationRandomizer equationRandomizer;

    private FacebookRepository facebookRepository;

    public FacebookQuiz(QuizAnswerChecker answerChecker, EquationRandomizer equationRandomizer, FacebookRepository facebookRepository) {
        this.answerChecker = answerChecker;
        this.equationRandomizer = equationRandomizer;
        this.facebookRepository = facebookRepository;
    }

    public String generateQuiz(String id) {
        FacebookIdQuizInfo idQuizInfo = new FacebookIdQuizInfo();
        idQuizInfo.setQuiz(equationRandomizer.randomEquation());
        idQuizInfo.setId(id);
        facebookRepository.save(idQuizInfo);
        return idQuizInfo.getQuiz();
    }

    public boolean checkQuiz(String id, String answer) {
        return answerChecker.checkForCorrectAnswer(facebookRepository.findById(id).get().quiz, answer);
    }
}
