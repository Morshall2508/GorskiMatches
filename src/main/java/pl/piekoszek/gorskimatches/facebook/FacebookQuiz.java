package pl.piekoszek.gorskimatches.facebook;

import org.springframework.stereotype.Component;
import pl.piekoszek.gorskimatches.config.http.NotFoundException;
import pl.piekoszek.gorskimatches.equation.EquationRandomizer;
import pl.piekoszek.gorskimatches.equation.QuizAnswerChecker;

@Component
class FacebookQuiz {

    private final QuizAnswerChecker answerChecker;

    private final EquationRandomizer equationRandomizer;

    private final FacebookRepository facebookRepository;

    public FacebookQuiz(QuizAnswerChecker answerChecker, EquationRandomizer equationRandomizer, FacebookRepository facebookRepository) {
        this.answerChecker = answerChecker;
        this.equationRandomizer = equationRandomizer;
        this.facebookRepository = facebookRepository;
    }

    String generateQuiz(String id) {
        FacebookIdQuizInfo idQuizInfo = new FacebookIdQuizInfo();
        idQuizInfo.setQuiz(equationRandomizer.randomEquation());
        idQuizInfo.setId(id);
        facebookRepository.save(idQuizInfo);
        return idQuizInfo.getQuiz();
    }

    boolean checkQuiz(String id, String answer) {
        return answerChecker.checkForCorrectAnswer(facebookRepository.findById(id).orElseThrow(()
                -> new NotFoundException("Cannot find the quiz with id: " + id)).quiz, answer);
    }

    boolean isStarted(String id) {
        return facebookRepository.findById(id).isPresent();
    }

    void cleanUpAfterQuiz(String id) {
        facebookRepository.deleteById(id);
    }
}
