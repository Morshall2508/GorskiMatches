package pl.piekoszek.gorskimatches.facebook;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import pl.piekoszek.gorskimatches.equation.EquationRandomizer;
import pl.piekoszek.gorskimatches.equation.QuizAnswerChecker;

@Component
public class FacebookMessageService {

    private final RestTemplate template = new RestTemplate();

    private final String server;
    private final EquationRandomizer equationRandomizer;

    private final QuizAnswerChecker quizAnswerChecker;

    private FacebookMessageService(EquationRandomizer equationRandomizer, @Value("${matches.server.address}") String server, QuizAnswerChecker quizAnswerChecker) {
        this.equationRandomizer = equationRandomizer;
        this.server = server;
        this.quizAnswerChecker = quizAnswerChecker;
    }

    @Value("${PAGE_TOKEN}")
    private String PAGE_TOKEN;

    void sendHelloReply(String id) {
        FacebookMessageResponse response = new FacebookMessageResponse();
        response.setRecipient(new FacebookRecipient(id));
        response.setMessage(new FacebookMessage("hello"));
        HttpEntity<FacebookMessageResponse> entity = new HttpEntity<>(response);
        String result = template.postForEntity("https://graph.facebook.com/v2.6/me/messages?access_token="
                + PAGE_TOKEN, entity, String.class).getBody();
    }

    void sendAttachmentPhoto(String id) {
        FacebookAttachmentResponse response = new FacebookAttachmentResponse();
        response.setRecipient(new FacebookRecipient(id));
        response.setMessage(new FacebookAttachmentMessage());
        response.getMessage().setAttachment(new FacebookAttachment("image", new FacebookPayload("https://maciej.piekoszek.pl/api/image/equation/fb/" + equationRandomizer.randomEquation(), true)));
        HttpEntity<FacebookAttachmentResponse> entity = new HttpEntity<>(response);
        String result = template.postForEntity("https://graph.facebook.com/v2.6/me/messages?access_token="
                + PAGE_TOKEN, entity, String.class).getBody();
    }

    void sendResult(String id, String quiz, String answer) {
        FacebookMessageResponse response = new FacebookMessageResponse();
        response.setRecipient(new FacebookRecipient(id));
        if (quizAnswerChecker.checkForCorrectAnswer(quiz, answer)) {
            response.setMessage(new FacebookMessage("Great Job!"));
        } else {
            response.setMessage(new FacebookMessage("Hmm, try again!"));
        }
        HttpEntity<FacebookMessageResponse> entity = new HttpEntity<>(response);
        String result = template.postForEntity("https://graph.facebook.com/v2.6/me/messages?access_token="
                + PAGE_TOKEN, entity, String.class).getBody();
    }
}
