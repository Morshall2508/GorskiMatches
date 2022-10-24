package pl.piekoszek.gorskimatches.facebook;

public class FacebookAttachment {

    String type;
    FacebookPayload payload;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setPayload(FacebookPayload payload) {
        this.payload = payload;
    }

    public FacebookAttachment(String type, FacebookPayload payload) {
        this.type = type;
        this.payload = payload;
    }
}
