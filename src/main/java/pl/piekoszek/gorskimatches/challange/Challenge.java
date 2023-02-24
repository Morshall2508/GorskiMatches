package pl.piekoszek.gorskimatches.challange;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity

public class Challenge {

    @Id
    @Type(type = "org.hibernate.type.UUIDCharType")
    private UUID uuid;

    private String emailUser1;

    private String emailUser2;

    private int totalScoreUser1;

    private int totalScoreUser2;

    private float timeToSolveUser1;

    private float timeToSolveUser2;

    private long creationTime;

    @Enumerated(EnumType.STRING)
    private Result result;

    @OneToMany(cascade = CascadeType.ALL)
    private List<ChallengeQuiz> challengeQuizzes;

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getEmailUser1() {
        return emailUser1;
    }

    public void setEmailUser1(String emailUser1) {
        this.emailUser1 = emailUser1;
    }

    public String getEmailUser2() {
        return emailUser2;
    }

    public void setEmailUser2(String emailUser2) {
        this.emailUser2 = emailUser2;
    }

    public int getTotalScoreUser1() {
        return totalScoreUser1;
    }

    public void setTotalScoreUser1(int totalScoreUser1) {
        this.totalScoreUser1 = totalScoreUser1;
    }

    public int getTotalScoreUser2() {
        return totalScoreUser2;
    }

    public void setTotalScoreUser2(int totalScoreUser2) {
        this.totalScoreUser2 = totalScoreUser2;
    }

    public float getTimeToSolveUser1() {
        return timeToSolveUser1;
    }

    public void setTimeToSolveUser1(float timeToSolveUser1) {
        this.timeToSolveUser1 = timeToSolveUser1;
    }

    public float getTimeToSolveUser2() {
        return timeToSolveUser2;
    }

    public void setTimeToSolveUser2(float timeToSolveUser2) {
        this.timeToSolveUser2 = timeToSolveUser2;
    }

    public long getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(long creationTime) {
        this.creationTime = creationTime;
    }

    public List<ChallengeQuiz> getChallengeQuizzes() {
        return challengeQuizzes;
    }

    public void setChallengeQuizzes(List<ChallengeQuiz> challengeQuizzes) {
        this.challengeQuizzes = challengeQuizzes;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }
}
