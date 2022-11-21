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
        FacebookEntry facebookEntry = new FacebookEntry();
        request.getEntry().forEach(entry -> entry.getMessaging().forEach(message
                -> {
            facebookEntry.setId(message.getSender().get("id"));
            var responses = commands.handleCommands(message.getMessage(), facebookEntry.getId());
            for (FacebookCommandResponse commandResponse : responses) {
                if (commandResponse.getCommandsResponseType() == FacebookCommandsResponseType.ATTACHMENT_RESPONSE) {
                    messageService.sendAttachmentPhoto(facebookEntry.getId(), commandResponse.getResponse());
                } else {
                    messageService.sendReply(facebookEntry.getId(), commandResponse.getResponse());
                }
            }
        }));
    }
}
