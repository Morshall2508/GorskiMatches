package pl.piekoszek.gorskimatches.challange;

import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class ChallengeResult {

    private UUID uuid;

    private int score;

    private float time;

    public UUID getUuid() {
        return uuid;
    }

    public int getScore() {
        return score;
    }

    public float getTime() {
        return time;
    }


    public ChallengeResult(UUID uuid, int score, float time) {
        this.uuid = uuid;
        this.score = score;
        this.time = time;
    }
    public ChallengeResult(){}
}
