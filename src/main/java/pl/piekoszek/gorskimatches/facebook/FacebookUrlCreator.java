package pl.piekoszek.gorskimatches.facebook;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
class FacebookUrlCreator {

    private final String server;

    public FacebookUrlCreator(@Value("${matches.server.address}") String server) {
        this.server = server;
    }

    public String createUrl(String quiz) {
        return server + "api/image/equation/fb/" + quiz;
    }
}
