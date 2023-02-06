package pl.piekoszek.gorskimatches.challange;

import java.util.UUID;

class ChallengeRequest {

    private String sender;

    private String receiver;

    private UUID uuid;

    public String getSender() {
        return sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public UUID getUuid() {
        return uuid;
    }
}
