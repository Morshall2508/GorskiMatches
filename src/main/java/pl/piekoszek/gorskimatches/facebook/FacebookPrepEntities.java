package pl.piekoszek.gorskimatches.facebook;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class FacebookPrepEntities {

    @Value("${PAGE_TOKEN}")
    private String PAGE_TOKEN;

    private final String fbUrl = "https://graph.facebook.com/v2.6/me/messages?access_token=";
    private final RestTemplate template = new RestTemplate();

    public String getMessageEntity(FacebookMessageResponse response) {
        HttpEntity<FacebookResponse> entity = new HttpEntity<>(response);
        return template.postForEntity(fbUrl + PAGE_TOKEN, entity, String.class).getBody();
    }

    public String getAttachmentEntity(FacebookAttachmentResponse response) {
        HttpEntity<FacebookResponse> entity = new HttpEntity<>(response);
        return template.postForEntity(fbUrl + PAGE_TOKEN, entity, String.class).getBody();
    }
}
