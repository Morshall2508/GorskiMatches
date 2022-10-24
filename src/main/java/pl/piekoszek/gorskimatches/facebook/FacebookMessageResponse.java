package pl.piekoszek.gorskimatches.facebook;

import java.util.HashMap;
import java.util.Map;

public class FacebookMessageResponse {
    private String messageType;
    private Map<String, String> recipient = new HashMap<>();
    private Map<String, String> message = new HashMap<>();
    private FacebookAttachment attachment;

    public FacebookMessageResponse(String messageType, Map<String, String> recipient, Map<String, String> message, FacebookAttachment attachment) {
        this.messageType = messageType;
        this.recipient = recipient;
        this.message = message;
        this.attachment = attachment;
    }

    public void setAttachment(FacebookAttachment attachment) {
        this.attachment = attachment;
    }

    public FacebookMessageResponse() {

    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    public Map<String, String> getRecipient() {
        return recipient;
    }

    public Map<String, String> getMessage() {
        return message;
    }

}
