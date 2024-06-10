package pl.piekoszek.gorskimatches.messenger;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import pl.piekoszek.gorskimatches.config.http.NotFoundException;
import pl.piekoszek.gorskimatches.equation.EquationRandomizer;
import pl.piekoszek.gorskimatches.equation.QuizAnswerChecker;

@Component
public class QuizHandler {

    private final QuizAnswerChecker answerChecker;

    private final EquationRandomizer equationRandomizer;

    private final QuizRepository quizRepository;

    private final String server;

    public QuizHandler(QuizAnswerChecker answerChecker,
                       EquationRandomizer equationRandomizer,
                       QuizRepository quizRepository,
                       @Value("${matches.server.address}") String server) {
        this.server = server;
        this.answerChecker = answerChecker;
        this.equationRandomizer = equationRandomizer;
        this.quizRepository = quizRepository;
    }

    public String generateQuiz(String id) {
        IdQuizInfo idQuizInfo = new IdQuizInfo();
        idQuizInfo.setQuiz(equationRandomizer.randomEquation());
        idQuizInfo.setId(id);
        quizRepository.save(idQuizInfo);
        return idQuizInfo.getQuiz();
    }

    public String createUrl(String quiz) {
        return server + "api/image/equation/fb/" + quiz;
    }

    public boolean checkQuiz(String id, String answer) {
        return answerChecker.checkForCorrectAnswer(quizRepository.findById(id).orElseThrow(()
                -> new NotFoundException("Cannot find the quiz with id: " + id)).quiz, answer);
    }

    public boolean isStarted(String id) {
        return quizRepository.findById(id).isPresent();
    }

    public void cleanUpAfterQuiz(String id) {
        quizRepository.deleteById(id);
    }
}
