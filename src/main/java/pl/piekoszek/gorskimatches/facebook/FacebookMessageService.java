package pl.piekoszek.gorskimatches.facebook;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import pl.piekoszek.gorskimatches.equation.EquationRandomizer;
import pl.piekoszek.gorskimatches.equation.QuizAnswerChecker;

@Component
public class FacebookMessageService {

    private final String server;

    private final EquationRandomizer equationRandomizer;

    private final QuizAnswerChecker quizAnswerChecker;

    private final FacebookPrepEntities prepEntities;

    IdQuizMapper idQuizMapper = new IdQuizMapper();

    private FacebookMessageService(EquationRandomizer equationRandomizer,
                                   @Value("${matches.server.address}") String server,
                                   QuizAnswerChecker quizAnswerChecker,
                                   FacebookPrepEntities prepEntities) {
        this.equationRandomizer = equationRandomizer;
        this.server = server;
        this.quizAnswerChecker = quizAnswerChecker;
        this.prepEntities = prepEntities;
    }

    void sendReply(String id, String message) {
        FacebookMessageResponse response = new FacebookMessageResponse();
        response.setRecipient(new FacebookRecipient(id));
        response.setMessage(new FacebookMessage(message));
        prepEntities.getMessageEntity(response);
    }

    void sendAttachmentPhoto(String id) {
        FacebookAttachmentResponse response = new FacebookAttachmentResponse();
        response.setRecipient(new FacebookRecipient(id));
        response.setMessage(new FacebookAttachmentMessage());
        idQuizMapper.getIdToQuiz().put(id, equationRandomizer.randomEquation());
        response.getMessage().setAttachment(new FacebookAttachment("image", new FacebookPayload(server + "api/image/equation/fb/" + idQuizMapper.getIdToQuiz().get(id), true)));
        prepEntities.getAttachmentEntity(response);
    }

    void sendResult(String id, String quiz, String answer) {
        FacebookMessageResponse response = new FacebookMessageResponse();
        response.setRecipient(new FacebookRecipient(id));
        if (quizAnswerChecker.checkForCorrectAnswer(quiz, answer)) {
            response.setMessage(new FacebookMessage("Great Job! For another quiz, type in: challenge"));
        } else {
            response.setMessage(new FacebookMessage("Hmm, try again!"));
        }
        prepEntities.getMessageEntity(response);
    }
}
