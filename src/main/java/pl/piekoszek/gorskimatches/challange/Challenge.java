package pl.piekoszek.gorskimatches.challange;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;
import java.util.UUID;

@Entity
public class Challenge {
    @Id
    public UUID uuid;

    public String email;

    @OneToMany
    public List<ChallengeQuiz> challengeQuizzes;

    public int score1;

    public int score2;
}
