package pl.piekoszek.gorskimatches.facebook;

class FacebookCommandResponse {

    private final String response;

    private final FacebookCommandsResponseType commandsResponseType;

    static FacebookCommandResponse ofMessage(String response) {
        return new FacebookCommandResponse(response, FacebookCommandsResponseType.MESSAGE_RESPONSE);
    }

    static FacebookCommandResponse ofAttachment(String response) {
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
