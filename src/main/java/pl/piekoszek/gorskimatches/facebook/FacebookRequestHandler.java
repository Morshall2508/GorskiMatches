package pl.piekoszek.gorskimatches.facebook;

import org.springframework.stereotype.Component;

@Component
class FacebookRequestHandler {

    private final FacebookCommands commands;
    private final FacebookMessageService messageService;

    public FacebookRequestHandler(FacebookCommands commands, FacebookMessageService messageService) {
        this.commands = commands;
        this.messageService = messageService;
    }

    void handle(FacebookHookRequest request) {
        request.getEntry().forEach(entry -> entry.getMessaging().forEach(message -> {
            String id = message.getSender().get("id");
            var responses = commands.handleCommands(message.getMessage(), id);
            for (FacebookCommandResponse commandResponse : responses) {
                if (commandResponse.getCommandsResponseType() == FacebookCommandsResponseType.ATTACHMENT_RESPONSE) {
                    messageService.sendAttachmentPhoto(id, commandResponse.getResponse());
                } else {
                    messageService.sendReply(id, commandResponse.getResponse());
                }
            }
        }));
    }
}