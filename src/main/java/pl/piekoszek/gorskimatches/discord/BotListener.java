package pl.piekoszek.gorskimatches.discord;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.channel.middleman.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

class BotListener extends ListenerAdapter {

    private final RequestHandler requestHandler;

    public BotListener(RequestHandler requestHandler) {
        this.requestHandler = requestHandler;
    }

    public void onMessageReceived(MessageReceivedEvent event) {
        if (!event.getAuthor().isBot()) {
            Message message = event.getMessage();
            String content = message.getContentRaw();
            MessageChannel channel = event.getChannel();
            channel.sendMessage(requestHandler.reply(content, event)).queue();
        }
    }
}