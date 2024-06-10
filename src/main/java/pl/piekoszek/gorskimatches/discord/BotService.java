package pl.piekoszek.gorskimatches.discord;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Objects;

@Service
class BotService {

    private final RequestHandler requestHandler;
    private final String discordToken;
    private JDA jda;

    public BotService(@Value("${discord.token}") String discordToken, RequestHandler requestHandler) {
        this.discordToken = discordToken;
        this.requestHandler = requestHandler;
    }

    @PostConstruct
    public void init() {
        try {
            this.jda = JDABuilder.createDefault(discordToken, GatewayIntent.GUILD_MESSAGES, GatewayIntent.MESSAGE_CONTENT, GatewayIntent.GUILD_MEMBERS)
                    .addEventListeners(new BotListener(requestHandler)).build();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendMessage(String guild, String channel, String message) {
        try {
            Objects.requireNonNull(Objects.requireNonNull(jda
                                    .getGuildById(guild))
                            .getTextChannelById(channel))
                    .sendMessage(message)
                    .queue();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}