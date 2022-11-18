package pl.piekoszek.gorskimatches.challange;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity

public class Challenge {
    @Id
    @Type(type = "org.hibernate.type.UUIDCharType")
    private UUID uuid;

    private String email;

    private int registeredUserScore;

    private int nonRegisteredUserScore;

    private float nonRegisteredUserTimeSeconds;

    private float registeredUserTimeSeconds;

    @OneToMany(cascade = CascadeType.ALL)
    private List<ChallengeQuiz> challengeQuizzes;

    @Temporal(TemporalType.DATE)
    Date creationDate;

    @Temporal(TemporalType.TIME)
    Date creationTime;

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

    public float getNonRegisteredUserTimeSeconds() {
        return nonRegisteredUserTimeSeconds;
    }

    public float getRegisteredUserTimeSeconds() {
        return registeredUserTimeSeconds;
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

    public void setNonRegisteredUserTimeSeconds(float nonRegisteredUserTimeSeconds) {
        this.nonRegisteredUserTimeSeconds = nonRegisteredUserTimeSeconds;
    }

    public void setRegisteredUserTimeSeconds(float registeredUserTimeSeconds) {
        this.registeredUserTimeSeconds = registeredUserTimeSeconds;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }
}
