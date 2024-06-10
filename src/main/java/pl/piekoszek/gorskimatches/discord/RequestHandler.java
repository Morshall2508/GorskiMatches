package pl.piekoszek.gorskimatches.discord;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.springframework.stereotype.Component;

import pl.piekoszek.gorskimatches.messenger.QuizHandler;

import java.util.List;

@Component
class RequestHandler {

    private final Commands commands;

    public RequestHandler(QuizHandler quizHandler) {
        this.commands = new Commands(quizHandler);
    }

    public String reply(String messageReceived, MessageReceivedEvent event) {
        List<String> responses = commands.handleCommands(messageReceived, event.getAuthor().getId());
        return String.join("\n", responses);
    }
}