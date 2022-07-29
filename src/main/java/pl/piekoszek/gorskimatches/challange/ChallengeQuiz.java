package pl.piekoszek.gorskimatches.challange;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class ChallengeQuiz {

    @Id
    @GeneratedValue
    public long id;

    @ManyToOne
    public Challenge challenge;

    public String quiz;

}
