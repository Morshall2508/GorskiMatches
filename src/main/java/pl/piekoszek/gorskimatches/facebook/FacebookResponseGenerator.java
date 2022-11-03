package pl.piekoszek.gorskimatches.facebook;

import org.springframework.stereotype.Component;

@Component
public class FacebookResponseGenerator {


    public FacebookMessageResponse messageResponse(String id){
        FacebookMessageResponse response = new FacebookMessageResponse();
        response.setRecipient(new FacebookRecipient(id));
        response.setMessage(new FacebookMessage(""));
        return response;
    }

    public FacebookAttachmentResponse attachmentResponse(String id){
        FacebookAttachmentResponse response = new FacebookAttachmentResponse();
        response.setRecipient(new FacebookRecipient(id));
        response.setMessage(new FacebookAttachmentMessage());
        response.getMessage().setAttachment(new FacebookAttachment("image", new FacebookPayload("", true)));
        return response;
    }

}
