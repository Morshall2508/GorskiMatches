package pl.piekoszek.gorskimatches.challange;

public class ScoreInfo {

    public int score;

    public float time;

    public ScoreInfo(){

    }
    public ScoreInfo (int score, float time){
        this.score = score;
        this.time = time;
    }

    public int getScore() {
        return score;
    }

    public float getTime() {
        return time;
    }
}
