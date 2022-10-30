package pl.piekoszek.gorskimatches.facebook;

import java.util.HashMap;
import java.util.Map;

public class IdQuizMapper {

    private Map<String, String> idToQuiz = new HashMap<>();

    public Map<String, String> getIdToQuiz() {
        return idToQuiz;
    }

    public void setIdToQuiz(Map<String, String> idToQuiz) {
        this.idToQuiz = idToQuiz;
    }
}
