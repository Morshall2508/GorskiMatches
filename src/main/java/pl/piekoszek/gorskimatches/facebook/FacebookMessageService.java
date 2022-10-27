package pl.piekoszek.gorskimatches.facebook;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import pl.piekoszek.gorskimatches.equation.EquationRandomizer;
import pl.piekoszek.gorskimatches.equation.QuizAnswerChecker;
import pl.piekoszek.gorskimatches.validation.StringEditor;

import java.util.HashMap;
import java.util.Map;

@Component
public class FacebookMessageService {

    private final RestTemplate template = new RestTemplate();

    private final String server;

    private Map<String, String> idToQuiz = new HashMap<>();
    private final EquationRandomizer equationRandomizer;

    private final QuizAnswerChecker quizAnswerChecker;

    private final StringEditor stringEditor;

    private FacebookMessageService(EquationRandomizer equationRandomizer, @Value("${matches.server.address}") String server, QuizAnswerChecker quizAnswerChecker, StringEditor stringEditor) {
        this.equationRandomizer = equationRandomizer;
        this.server = server;
        this.quizAnswerChecker = quizAnswerChecker;
        this.stringEditor = stringEditor;
    }

    @Value("${PAGE_TOKEN}")
    private String PAGE_TOKEN;

    void sendReply (String id, String message){
        FacebookMessageResponse response = new FacebookMessageResponse();
        response.setRecipient(new FacebookRecipient(id));
        response.setMessage(new FacebookMessage(message));
        HttpEntity<FacebookMessageResponse> entity = new HttpEntity<>(response);
        String result = template.postForEntity("https://graph.facebook.com/v2.6/me/messages?access_token="
                + PAGE_TOKEN, entity, String.class).getBody();
    }

    Map<String, String> sendAttachmentPhoto(String id) {
        FacebookAttachmentResponse response = new FacebookAttachmentResponse();
        response.setRecipient(new FacebookRecipient(id));
        response.setMessage(new FacebookAttachmentMessage());
        idToQuiz.put(id, equationRandomizer.randomEquation());
        response.getMessage().setAttachment(new FacebookAttachment("image", new FacebookPayload("https://maciej.piekoszek.pl/api/image/equation/fb/" + idToQuiz.get(id), true)));
        HttpEntity<FacebookAttachmentResponse> entity = new HttpEntity<>(response);
        String result = template.postForEntity("https://graph.facebook.com/v2.6/me/messages?access_token="
                + PAGE_TOKEN, entity, String.class).getBody();
        return idToQuiz;
    }

    void sendResult(String id, String quiz, String answer) {
        FacebookMessageResponse response = new FacebookMessageResponse();
        response.setRecipient(new FacebookRecipient(id));
        if (quizAnswerChecker.checkForCorrectAnswer(quiz, answer)) {
            response.setMessage(new FacebookMessage("Great Job! For another quiz, type in: challenge"));
        } else {
            response.setMessage(new FacebookMessage("Hmm, try again!"));
        }
        HttpEntity<FacebookMessageResponse> entity = new HttpEntity<>(response);
        String result = template.postForEntity("https://graph.facebook.com/v2.6/me/messages?access_token="
                + PAGE_TOKEN, entity, String.class).getBody();
    }

    void handle(FacebookHookRequest request) {
        FacebookEntry facebookEntry = new FacebookEntry();
        request.getEntry().forEach(entry -> entry.getMessaging().forEach(message
                -> {
            facebookEntry.setId(message.getSender().get("id"));
            if (message.getMessage().getText().toLowerCase().matches(stringEditor.removeSpaces("challenge"))) {
                sendAttachmentPhoto(facebookEntry.getId());
            } else if (message.getMessage().getText().matches(stringEditor.removeSpaces("\\b\\s*\\d\\s*[+-]\\s*\\d\\s*=\\s*\\d\\s*\\b\\s*"))) {
                sendResult(facebookEntry.getId(), idToQuiz.get(facebookEntry.getId()), stringEditor.removeSpaces(message.getMessage().getText()));
            } else {
                sendReply(facebookEntry.getId(), "Hello!");
                sendReply(facebookEntry.getId(), "Welcome to my facebook site! Here you can solve as in quizzes on the matchbook that say: Move one match to make equation correct.\nFor quiz simply type in: challenge. You will receive a quiz to solve, then type in your answer in format : 0+0=0\nGood luck!");
                sendReply(facebookEntry.getId(), "For quiz simply type in: challenge. You will receive a quiz to solve, then type in your answer in format : 0+0=0\nGood luck!");
            }
        }));
    }
}
