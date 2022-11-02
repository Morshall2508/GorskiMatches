package pl.piekoszek.gorskimatches.facebook;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import pl.piekoszek.gorskimatches.equation.EquationRandomizer;
import pl.piekoszek.gorskimatches.equation.QuizAnswerChecker;
import pl.piekoszek.gorskimatches.repository.FacebookRepository;

@Component
public class FacebookMessageService {

    private final String server;

    private final QuizAnswerChecker quizAnswerChecker;

    private final FacebookPrepEntities prepEntities;

    private FacebookRepository facebookRepository;

    private FacebookQuiz facebookQuiz;

    private FacebookMessageService(@Value("${matches.server.address}") String server,
                                   QuizAnswerChecker quizAnswerChecker,
                                   FacebookPrepEntities prepEntities,
                                   FacebookRepository facebookRepository,
                                   FacebookQuiz facebookQuiz) {
        this.server = server;
        this.quizAnswerChecker = quizAnswerChecker;
        this.prepEntities = prepEntities;
        this.facebookRepository = facebookRepository;
        this.facebookQuiz = facebookQuiz;
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
        response.getMessage().setAttachment(new FacebookAttachment("image", new FacebookPayload(server + "api/image/equation/fb/" + facebookRepository.findById(id).get().quiz, true)));
        prepEntities.getAttachmentEntity(response);
    }

    void sendResult(String id, String answer) {
        FacebookMessageResponse response = new FacebookMessageResponse();
        response.setRecipient(new FacebookRecipient(id));
        if (facebookQuiz.checkQuiz(id,answer)) {
            response.setMessage(new FacebookMessage("Great Job! For another quiz, type in: challenge"));
        } else {
            response.setMessage(new FacebookMessage("Hmm, try again!"));
        }
        prepEntities.getMessageEntity(response);
    }
}
