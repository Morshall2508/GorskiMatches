package pl.piekoszek.gorskimatches.facebook;

public class FacebookAttachmentResponse extends FacebookResponse{

    private FacebookAttachmentMessage message;

    public FacebookAttachmentResponse(FacebookAttachmentMessage message) {
        this.message = message;
    }

    public FacebookAttachmentResponse() {

    }

    public FacebookAttachmentMessage getMessage() {
        return message;
    }

    public void setMessage(FacebookAttachmentMessage message) {
        this.message = message;
    }
}
