package pl.piekoszek.gorskimatches.challange;

import org.springframework.stereotype.Component;

@Component
class Judge {

    Result getResultForChallengeUser(int scoreUser1, int scoreUser2, float timeUser1, float timeUser2) {
        if (scoreUser1 != scoreUser2) {
            if (scoreUser1 > scoreUser2) {
                return Result.USER_1_WIN;
            } else {
                return Result.USER_2_WIN;
            }
        } else {
            if (timeUser1 < timeUser2) {
                return Result.USER_1_WIN;
            } else {
                return Result.USER_2_WIN;
            }
        }
    }
}
