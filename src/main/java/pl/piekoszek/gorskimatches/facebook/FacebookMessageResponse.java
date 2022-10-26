package pl.piekoszek.gorskimatches.facebook;

public class FacebookMessageResponse {
    private FacebookRecipient recipient;

    private FacebookMessage message;

    public FacebookMessageResponse() {

    }

    public FacebookRecipient getRecipient() {
        return recipient;
    }

    public void setRecipient(FacebookRecipient recipient) {
        this.recipient = recipient;
    }

    public FacebookMessage getMessage() {
        return message;
    }

    public void setMessage(FacebookMessage message) {
        this.message = message;
    }
}
