package pl.piekoszek.gorskimatches.challange;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;
import java.util.UUID;

@Entity
public class Challenge {
    @Id
    private UUID uuid;

    private String email;

    private int registeredUserScore;

    private int nonRegisteredUserScore;

    private float nonRegisteredUserTime;

    private float registeredUserTime;

    @OneToMany(cascade = CascadeType.ALL)
    private List<ChallengeQuiz> challengeQuizzes;

    public List<ChallengeQuiz> getChallengeQuizzes() {
        return challengeQuizzes;
    }

    public void setChallengeQuizzes(List<ChallengeQuiz> challengeQuizzes) {
        this.challengeQuizzes = challengeQuizzes;
    }

    public UUID getUuid() {
        return uuid;
    }

    public String getEmail() {
        return email;
    }

    public int getRegisteredUserScore() {
        return registeredUserScore;
    }

    public int getNonRegisteredUserScore() {
        return nonRegisteredUserScore;
    }

    public float getNonRegisteredUserTime() {
        return nonRegisteredUserTime;
    }

    public float getRegisteredUserTime() {
        return registeredUserTime;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setRegisteredUserScore(int registeredUserScore) {
        this.registeredUserScore = registeredUserScore;
    }

    public void setNonRegisteredUserScore(int nonRegisteredUserScore) {
        this.nonRegisteredUserScore = nonRegisteredUserScore;
    }

    public void setNonRegisteredUserTime(float nonRegisteredUserTime) {
        this.nonRegisteredUserTime = nonRegisteredUserTime;
    }

    public void setRegisteredUserTime(float registeredUserTime) {
        this.registeredUserTime = registeredUserTime;
    }
}
