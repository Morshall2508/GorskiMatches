package pl.piekoszek.gorskimatches.facebook;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class FacebookIdQuizInfo {


    @Id
    String id;

    String quiz;

    public FacebookIdQuizInfo(){

    }

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
