package pl.piekoszek.gorskimatches.facebook;

import java.util.ArrayList;
import java.util.List;

public class FacebookHookRequest {
    private String object;
    private List<FacebookEntry> entry = new ArrayList<>();

    public List<FacebookEntry> getEntry() {
        return entry;
    }

}
