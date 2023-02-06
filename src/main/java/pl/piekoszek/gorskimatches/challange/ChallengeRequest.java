package pl.piekoszek.gorskimatches.challange;

import java.util.UUID;

class ChallengeRequest {

    private String receiver;

    private String initiator;

    private UUID uuid;

    public String getReceiver() {
        return receiver;
    }

    public String getInitiator() {
        return initiator;
    }

    public UUID getUuid() {
        return uuid;
    }
}
