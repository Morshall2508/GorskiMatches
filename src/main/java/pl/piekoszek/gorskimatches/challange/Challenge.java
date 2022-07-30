package pl.piekoszek.gorskimatches.challange;

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

//    @OneToMany
//    private List<ChallengeQuiz> challengeQuizzes;

    private int registeredUserScore;

    private int nonRegisteredUserScore;

    public Challenge(String email, int registeredUserScore, int nonRegisteredUserScore) {
        this.email = email;

        this.registeredUserScore = registeredUserScore;
        this.nonRegisteredUserScore = nonRegisteredUserScore;
    }

    public Challenge() {

    }

    public UUID getUuid() {
        return uuid;
    }

    public String getEmail() {
        return email;
    }

//    public List<ChallengeQuiz> getChallengeQuizzes(){
//        return challengeQuizzes;
//    }

    public int getRegisteredUserScore() {
        return registeredUserScore;
    }

    public int getNonRegisteredUserScore() {
        return nonRegisteredUserScore;
    }

}
