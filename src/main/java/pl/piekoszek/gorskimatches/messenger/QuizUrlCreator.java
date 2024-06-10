package pl.piekoszek.gorskimatches.messenger;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class QuizUrlCreator {

    private final String server;

    public QuizUrlCreator(@Value("${matches.server.address}") String server) {
        this.server = server;
    }

    public String createUrl(String quiz) {
        return server + "api/image/equation/fb/" + quiz;
    }
}
