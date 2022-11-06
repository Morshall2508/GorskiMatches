package pl.piekoszek.gorskimatches.facebook;

public class FacebookCommandResponse {
    private String response;

    private final FacebookCommandsResponseType commandsResponseType;

    public static FacebookCommandResponse ofMessage(String response) {
        return new FacebookCommandResponse(response, FacebookCommandsResponseType.MESSAGE_RESPONSE);
    }

    public static FacebookCommandResponse ofAttachment(String response) {
        return new FacebookCommandResponse(response, FacebookCommandsResponseType.ATTACHMENT_RESPONSE);
    }

    private FacebookCommandResponse(String response, FacebookCommandsResponseType commandsResponseType) {
        this.response = response;
        this.commandsResponseType = commandsResponseType;
    }

    public String getResponse() {
        return response;
    }

    public FacebookCommandsResponseType getCommandsResponseType() {
        return commandsResponseType;
    }
}
