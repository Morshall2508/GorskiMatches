package pl.piekoszek.gorskimatches.challange;

import java.util.List;
import java.util.UUID;

class ChallengeResult {

    private UUID uuid;

    private List<String> answersForChallenge;

    private float timeToSolveChallenge;

    public UUID getUuid() {
        return uuid;
    }

    public List<String> getAnswersForChallenge() {
        return answersForChallenge;
    }

    public float getTimeToSolveChallenge() {
        return timeToSolveChallenge;
    }
}
