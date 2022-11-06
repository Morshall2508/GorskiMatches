package pl.piekoszek.gorskimatches.facebook;

public class FacebookPayload {
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
