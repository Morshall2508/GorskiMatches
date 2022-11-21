package pl.piekoszek.gorskimatches.facebook;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

class FacebookHookRequest {

    private final List<FacebookEntry> entry = new ArrayList<>();

    public List<FacebookEntry> getEntry() {
        return entry;
    }

}

class FacebookEntry {

    private String id;
    private Long time;
    public List<FacebookMessaging> messaging = new ArrayList<>();

    public Long getTime() {
        return time;
    }

    public List<FacebookMessaging> getMessaging() {
        return messaging;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}

class FacebookMessaging {

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

class FacebookMessageReceived {

    private String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}