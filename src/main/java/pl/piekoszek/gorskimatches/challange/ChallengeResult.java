package pl.piekoszek.gorskimatches.challange;

import java.util.List;
import java.util.UUID;

class ChallengeResult {

    private UUID uuid;

    private List<String> answersUser1;

    private List<String> answersUser2;

    private float timeToSolveUser1;

    private float timeToSolveUser2;

    public UUID getUuid() {
        return uuid;
    }

    public List<String> getAnswersUser1() {
        return answersUser1;
    }

    public List<String> getAnswersUser2() {
        return answersUser2;
    }

    public float getTimeToSolveUser1() {
        return timeToSolveUser1;
    }

    public float getTimeToSolveUser2() {
        return timeToSolveUser2;
    }
}
