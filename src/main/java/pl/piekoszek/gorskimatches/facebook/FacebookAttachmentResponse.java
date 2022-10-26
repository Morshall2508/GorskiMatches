package pl.piekoszek.gorskimatches.facebook;


public class FacebookAttachmentResponse {
    private FacebookRecipient recipient;

    private FacebookAttachmentMessage message;

    public FacebookAttachmentResponse(FacebookRecipient recipient, FacebookAttachmentMessage message) {
        this.recipient = recipient;
        this.message = message;
    }

    public FacebookAttachmentResponse() {

    }

    public FacebookRecipient getRecipient() {
        return recipient;
    }

    public void setRecipient(FacebookRecipient recipient) {
        this.recipient = recipient;
    }

    public FacebookAttachmentMessage getMessage() {
        return message;
    }

    public void setMessage(FacebookAttachmentMessage message) {
        this.message = message;
    }
}
