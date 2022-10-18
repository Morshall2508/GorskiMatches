package pl.piekoszek.gorskimatches.facebook;

import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Component
public class FacebookEntry implements Serializable {
    private String id;
    private Long time;
    private List<FacebookMessaging> messaging = new ArrayList<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<FacebookMessaging> getMessaging() {
        return messaging;
    }

}
