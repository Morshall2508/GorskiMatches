package pl.piekoszek.gorskimatches.facebook;

class FacebookResponse {

    private FacebookRecipient recipient;

    public FacebookRecipient getRecipient() {
        return recipient;
    }

    public void setRecipient(FacebookRecipient recipient) {
        this.recipient = recipient;
    }

}

class FacebookRecipient {

    private String id;

    public FacebookRecipient(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}

class FacebookPayload {

    String url;

    boolean is_reusable;

    public FacebookPayload(String url, boolean is_reusable) {
        this.url = url;
        this.is_reusable = is_reusable;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public boolean isIs_reusable() {
        return is_reusable;
    }

    public void setIs_reusable(boolean is_reusable) {
        this.is_reusable = is_reusable;
    }
}

class FacebookMessage {

    private String text;

    public FacebookMessage(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}

class FacebookMessageResponse extends FacebookResponse {

    private FacebookMessage message;

    public FacebookMessage getMessage() {
        return message;
    }

    public void setMessage(FacebookMessage message) {
        this.message = message;
    }
}

class FacebookAttachmentResponse extends FacebookResponse {

    private FacebookAttachmentMessage message;

    public FacebookAttachmentResponse() {

    }

    public FacebookAttachmentMessage getMessage() {
        return message;
    }

    public void setMessage(FacebookAttachmentMessage message) {
        this.message = message;
    }
}

class FacebookAttachmentMessage {

    FacebookAttachment attachment;

    public FacebookAttachment getAttachment() {
        return attachment;
    }

    public void setAttachment(FacebookAttachment attachment) {
        this.attachment = attachment;
    }

}

class FacebookAttachment {

    String type;

    FacebookPayload payload;

    public FacebookAttachment(String type, FacebookPayload payload) {
        this.type = type;
        this.payload = payload;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public FacebookPayload getPayload() {
        return payload;
    }

    public void setPayload(FacebookPayload payload) {
        this.payload = payload;
    }
}

