package pl.piekoszek.gorskimatches.facebook;

import org.springframework.stereotype.Component;

@Component
public class FacebookRequestHandler {

    private final FacebookCommands commands;


    public FacebookRequestHandler(FacebookCommands commands) {
        this.commands = commands;
    }

    void handle(FacebookHookRequest request) {
        FacebookEntry facebookEntry = new FacebookEntry();
        request.getEntry().forEach(entry -> entry.getMessaging().forEach(message
                -> {
            facebookEntry.setId(message.getSender().get("id"));
            commands.helloMessage(message.getMessage(), facebookEntry.getId());
            commands.quiz(message.getMessage(), facebookEntry.getId());
            commands.check(message.getMessage(), facebookEntry.getId());
            commands.info(message.getMessage(), facebookEntry.getId());
            commands.contact(message.getMessage(), facebookEntry.getId());
            commands.commands(message.getMessage(), facebookEntry.getId());

        }));
    }
}
