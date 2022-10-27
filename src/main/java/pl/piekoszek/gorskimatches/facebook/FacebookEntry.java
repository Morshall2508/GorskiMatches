package pl.piekoszek.gorskimatches.facebook;

import java.util.ArrayList;
import java.util.List;

public class FacebookEntry {

    private String id;
    private Long time;
    public List<FacebookMessaging> messaging = new ArrayList<>();

    public Long getTime() {
        return time;
    }

    public List<FacebookMessaging> getMessaging() {
        return messaging;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
