package pl.piekoszek.gorskimatches.challange;

import org.springframework.stereotype.Component;

import javax.persistence.*;
@Component
@Entity
public class ChallengeQuiz {

    @Id
    @GeneratedValue
    public long id;

    @ManyToOne
    public Challenge challenge;

    public String quiz;

    public ChallengeQuiz(Challenge challenge, String quiz){
        this.challenge = challenge;
        this.quiz = quiz;
    }

    public ChallengeQuiz(){}

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
