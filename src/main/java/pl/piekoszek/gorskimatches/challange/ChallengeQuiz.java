package pl.piekoszek.gorskimatches.challange;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;

@Entity
public class ChallengeQuiz {

    @Id
    @GeneratedValue
    public long id;

    @JsonIgnore
    @ManyToOne
    public Challenge challenge;

    public String quiz;

    private String answerUser1;

    private String answerUser2;

    private int scoreUser1;

    private int scoreUser2;


    public ChallengeQuiz() {

    }

    public String getAnswerUser1() {
        return answerUser1;
    }

    public void setAnswerUser1(String answerUser1) {
        this.answerUser1 = answerUser1;
    }

    public String getAnswerUser2() {
        return answerUser2;
    }

    public void setAnswerUser2(String answerUser2) {
        this.answerUser2 = answerUser2;
    }

    public int getScoreUser1() {
        return scoreUser1;
    }

    public void setScoreUser1(int scoreUser1) {
        this.scoreUser1 = scoreUser1;
    }

    public int getScoreUser2() {
        return scoreUser2;
    }

    public void setScoreUser2(int scoreUser2) {
        this.scoreUser2 = scoreUser2;
    }



    public ChallengeQuiz(Challenge challenge, String quiz){
        this.challenge = challenge;
        this.quiz = quiz;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Challenge getChallenge() {
        return challenge;
    }

    public void setChallenge(Challenge challenge) {
        this.challenge = challenge;
    }

    public String getQuiz() {
        return quiz;
    }

    public void setQuiz(String quiz) {
        this.quiz = quiz;
    }
}
