package pl.piekoszek.gorskimatches.facebook;

public class FacebookMessageReceived {

    private String mid;
    private Long seq;
    private String text;

    public FacebookMessageReceived(String mid, Long seq, String text) {
        this.mid = mid;
        this.seq = seq;
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
