package pl.piekoszek.gorskimatches.challange;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ChallengeHistory {

    private List<String> answerUser1;

    private List<String> answerUser2;

    private List<Integer> scoreUser1;

    private List<Integer> scoreUser2;

    public ChallengeHistory(List<String> answerUser1, List<String> answerUser2, List<Integer> scoreUser1, List<Integer> scoreUser2) {
        this.answerUser1 = answerUser1;
        this.answerUser2 = answerUser2;
        this.scoreUser1 = scoreUser1;
        this.scoreUser2 = scoreUser2;
    }

    public List<String> getAnswerUser1() {
        return answerUser1;
    }

    public void setAnswerUser1(List<String> answerUser1) {
        this.answerUser1 = answerUser1;
    }

    public List<String> getAnswerUser2() {
        return answerUser2;
    }

    public void setAnswerUser2(List<String> answerUser2) {
        this.answerUser2 = answerUser2;
    }

    public List<Integer> getScoreUser1() {
        return scoreUser1;
    }

    public void setScoreUser1(List<Integer> scoreUser1) {
        this.scoreUser1 = scoreUser1;
    }

    public List<Integer> getScoreUser2() {
        return scoreUser2;
    }

    public void setScoreUser2(List<Integer> scoreUser2) {
        this.scoreUser2 = scoreUser2;
    }
}


