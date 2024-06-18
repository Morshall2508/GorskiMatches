package pl.piekoszek.gorskimatches.discord;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.springframework.stereotype.Component;

import pl.piekoszek.gorskimatches.messenger.QuizHandler;

import java.util.List;

@Component
 class RequestHandler {

    private final DiscordCommands discordCommands;

    public RequestHandler(QuizHandler quizHandler) {
        this.discordCommands = new DiscordCommands(quizHandler);
    }

    public String reply(String messageReceived, MessageReceivedEvent event) {
        List<String> responses = discordCommands.handleCommands(messageReceived, event.getAuthor().getId());
        return String.join("\n", responses);
    }
}