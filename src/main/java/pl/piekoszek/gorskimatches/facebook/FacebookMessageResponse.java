package pl.piekoszek.gorskimatches.facebook;

public class FacebookMessageResponse extends FacebookResponse {

    private FacebookMessage message;

    public FacebookMessage getMessage() {
        return message;
    }

    public void setMessage(FacebookMessage message) {
        this.message = message;
    }
}
