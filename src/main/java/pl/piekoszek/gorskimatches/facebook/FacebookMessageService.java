package pl.piekoszek.gorskimatches.facebook;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class FacebookMessageService {

    private final RestTemplate template = new RestTemplate();

    @Value("${pagetoken.path}")
    private String PAGE_TOKEN;
    void sendHelloReply(String id) {
        FacebookMessageResponse response = new FacebookMessageResponse();
        response.setMessage_type("text");
        response.getRecipient().put("id", id);
        response.getMessage().put("text", "hello");
        HttpEntity<FacebookMessageResponse> entity = new HttpEntity<>(response);
        String result = template.postForEntity("https://graph.facebook.com/v2.6/me/messages?access_token="
                + PAGE_TOKEN, entity, String.class).getBody();
    }
}
