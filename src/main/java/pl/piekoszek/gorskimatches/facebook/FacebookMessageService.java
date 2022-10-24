package pl.piekoszek.gorskimatches.facebook;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class FacebookMessageService {

    private final RestTemplate template = new RestTemplate();

    @Value("${PAGE_TOKEN}")
    private String PAGE_TOKEN;

    void sendHelloReply(String id) {
        FacebookMessageResponse response = new FacebookMessageResponse();
        response.getRecipient().put("id", id);
        response.getMessage().put("text", "hello");
        HttpEntity<FacebookMessageResponse> entity = new HttpEntity<>(response);
        String result = template.postForEntity("https://graph.facebook.com/v2.6/me/messages?access_token="
                + PAGE_TOKEN, entity, String.class).getBody();
    }

    void sendAttachmentPhoto(String id){
        FacebookMessageResponse response = new FacebookMessageResponse();
        response.getRecipient().put("id", id);
        response.setAttachment(new FacebookAttachment("image", new FacebookPayload("https://www.creativefabrica.com/wp-content/uploads/2021/03/30/Blue-Sky-Sky-Backgrounds-texture-Graphics-10152238-2-580x386.jpg", true)));
        HttpEntity<FacebookMessageResponse> entity = new HttpEntity<>(response);
        String result = template.postForEntity("https://graph.facebook.com/v2.6/me/message_attachments?access_token="
                + PAGE_TOKEN, entity, String.class).getBody();
    }
}
