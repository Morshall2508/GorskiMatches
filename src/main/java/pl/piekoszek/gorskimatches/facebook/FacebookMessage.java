package pl.piekoszek.gorskimatches.facebook;

public class FacebookMessage {
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
