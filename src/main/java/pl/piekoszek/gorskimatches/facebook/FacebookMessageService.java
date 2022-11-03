package pl.piekoszek.gorskimatches.facebook;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class FacebookMessageService {

    private final String server;

    private final FacebookPrepEntities prepEntities;

    private final FacebookQuiz facebookQuiz;

    private final FacebookResponseGenerator responseGenerator;

    private FacebookMessageService(@Value("${matches.server.address}") String server,
                                   FacebookPrepEntities prepEntities,
                                   FacebookQuiz facebookQuiz,
                                   FacebookResponseGenerator responseGenerator) {
        this.server = server;
        this.prepEntities = prepEntities;
        this.facebookQuiz = facebookQuiz;
        this.responseGenerator = responseGenerator;
    }

    void sendReply(String id, String message) {
        var response = responseGenerator.messageResponse(id);
        response.getMessage().setText(message);
        prepEntities.getMessageEntity(response);
    }

    void sendAttachmentPhoto(String id) {
        var response = responseGenerator.attachmentResponse(id);
        response.getMessage().getAttachment().getPayload().setUrl(server + "api/image/equation/fb/" + facebookQuiz.generateQuiz(id));
        prepEntities.getAttachmentEntity(response);
    }

    void sendResult(String id, String answer) {
        var response = responseGenerator.messageResponse(id);
        if (facebookQuiz.checkQuiz(id, answer)) {
            response.getMessage().setText("Great Job! For another quiz, type in: challenge");
        } else {
            response.getMessage().setText("Hmm, try again!");
        }
        prepEntities.getMessageEntity(response);
    }
}
