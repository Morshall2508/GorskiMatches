package pl.piekoszek.gorskimatches.facebook;


import org.springframework.stereotype.Component;
import pl.piekoszek.gorskimatches.validation.StringEditor;

@Component
public class FacebookRequestHandler {

    private FacebookMessageService service;

    private final StringEditor stringEditor;

    public FacebookRequestHandler(FacebookMessageService service, StringEditor stringEditor) {
        this.stringEditor = stringEditor;
        this.service = service;
    }

    void handle(FacebookHookRequest request) {
        FacebookEntry facebookEntry = new FacebookEntry();
        request.getEntry().forEach(entry -> entry.getMessaging().forEach(message
                -> {
            facebookEntry.setId(message.getSender().get("id"));
            if (message.getMessage().getText().toLowerCase().matches(stringEditor.removeSpaces("challenge"))) {
                service.sendAttachmentPhoto(facebookEntry.getId());
            } else if (message.getMessage().getText().matches(stringEditor.removeSpaces("\\b\\s*\\d\\s*[+-]\\s*\\d\\s*=\\s*\\d\\s*\\b\\s*"))) {
                service.sendResult(facebookEntry.getId(), service.idQuizMapper.getIdToQuiz().get(facebookEntry.getId()), stringEditor.removeSpaces(message.getMessage().getText()));
            } else {
                service.sendReply(facebookEntry.getId(), "Hello!");
                service.sendReply(facebookEntry.getId(), "Welcome to my facebook site! Here you can solve as in quizzes on the matchbook that say: Move one match to make equation correct.");
                service.sendReply(facebookEntry.getId(), "For quiz simply type in: challenge. You will receive a quiz to solve, then type in your answer in format : 0+0=0\nGood luck!");
            }
        }));
    }
}
