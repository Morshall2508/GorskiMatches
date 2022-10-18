package pl.piekoszek.gorskimatches.facebook;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class FacebookMessageResponse implements Serializable {
    private String message_type;
    private Map<String,String> recipient = new HashMap<>();
    private Map<String,String> message = new HashMap<>();

    public FacebookMessageResponse(String message_type, Map<String, String> recipient, Map<String, String> message) {
        this.message_type = message_type;
        this.recipient = recipient;
        this.message = message;
    }

    public FacebookMessageResponse() {

    }

    public void setMessage_type(String message_type) {
        this.message_type = message_type;
    }

    public Map<String, String> getRecipient() {
        return recipient;
    }

    public Map<String, String> getMessage() {
        return message;
    }

}
