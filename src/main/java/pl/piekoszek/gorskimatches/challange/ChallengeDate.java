package pl.piekoszek.gorskimatches.challange;

import org.springframework.stereotype.Component;

@Component
class ChallengeDate {
    long currentTime(){
        return System.currentTimeMillis();
    }
}
