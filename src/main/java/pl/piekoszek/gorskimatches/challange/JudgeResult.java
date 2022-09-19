package pl.piekoszek.gorskimatches.challange;

import org.springframework.stereotype.Component;

@Component
public class JudgeResult {

    public Result getResultForChallengeUser1(int scoreUser1, int scoreUser2, float timeUser1, float timeUser2) {
        if (scoreUser1 != scoreUser2) {
            if (scoreUser1 > scoreUser2) {
                return Result.WINNER;
            } else {
                return Result.LOSER;
            }
        } else {
            if (timeUser1 < timeUser2) {
                return Result.WINNER;
            } else {
                return Result.LOSER;
            }
        }
    }

    public Result getResultForChallengeUser2 (int scoreUser1, int scoreUser2, float timeUser1, float timeUser2) {
        if (scoreUser1 != scoreUser2) {
            if (scoreUser2 > scoreUser1) {
                return Result.WINNER;
            } else {
                return Result.LOSER;
            }
        } else {
            if (timeUser2 < timeUser1) {
                return Result.WINNER;
            } else {
                return Result.LOSER;
            }
        }
    }
}
