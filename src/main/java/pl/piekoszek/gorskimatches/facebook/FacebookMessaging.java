package pl.piekoszek.gorskimatches.facebook;

import java.util.Map;

public class FacebookMessaging {

    private Map<String, String> sender;
    private Map<String, String> recipient;
    private Long timestamp;
    private FacebookMessageReceived message;

    public Map<String, String> getSender() {
        return sender;
    }

    public void setSender(Map<String, String> sender) {
        this.sender = sender;
    }

    public Map<String, String> getRecipient() {
        return recipient;
    }

    public void setRecipient(Map<String, String> recipient) {
        this.recipient = recipient;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public FacebookMessageReceived getMessage() {
        return message;
    }

    public void setMessage(FacebookMessageReceived message) {
        this.message = message;
    }
}
