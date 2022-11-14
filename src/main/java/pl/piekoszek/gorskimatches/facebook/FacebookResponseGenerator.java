package pl.piekoszek.gorskimatches.facebook;

import org.springframework.stereotype.Component;

@Component
class FacebookResponseGenerator {

    FacebookMessageResponse messageResponse(String id){
        FacebookMessageResponse response = new FacebookMessageResponse();
        response.setRecipient(new FacebookRecipient(id));
        response.setMessage(new FacebookMessage(""));
        return response;
    }

    FacebookAttachmentResponse attachmentResponse(String id){
        FacebookAttachmentResponse response = new FacebookAttachmentResponse();
        response.setRecipient(new FacebookRecipient(id));
        response.setMessage(new FacebookAttachmentMessage());
        response.getMessage().setAttachment(new FacebookAttachment("image", new FacebookPayload("", true)));
        return response;
    }
}
