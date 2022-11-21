package pl.piekoszek.gorskimatches.facebook;

import org.springframework.stereotype.Component;

@Component
class FacebookMessageService {

    private final FacebookApiClient facebookApiClient;

    private final FacebookResponseGenerator responseGenerator;


    private FacebookMessageService(FacebookApiClient facebookApiClient,
                                   FacebookResponseGenerator responseGenerator) {
        this.facebookApiClient = facebookApiClient;
        this.responseGenerator = responseGenerator;
    }

    void sendReply(String id, String message) {
        var response = responseGenerator.messageResponse(id);
        response.getMessage().setText(message);
        facebookApiClient.getMessageEntity(response);
    }

    void sendAttachmentPhoto(String id, String url) {
        var response = responseGenerator.attachmentResponse(id);
        response.getMessage().getAttachment().getPayload().setUrl(url);
        facebookApiClient.getAttachmentEntity(response);
    }
}
