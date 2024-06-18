package pl.piekoszek.gorskimatches.messenger;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class IdQuizInfo {

    @Id
    String id;

    String quiz;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getQuiz() {
        return quiz;
    }

    public void setQuiz(String quiz) {
        this.quiz = quiz;
    }
}
