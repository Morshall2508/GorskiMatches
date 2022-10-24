package pl.piekoszek.gorskimatches.facebook;

public class FacebookPayload {
    String url;
    boolean reusable;

    public FacebookPayload(String url, boolean reusable) {
        this.url = url;
        this.reusable = reusable;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isReusable() {
        return reusable;
    }

    public void setReusable(boolean reusable) {
        this.reusable = reusable;
    }
}
