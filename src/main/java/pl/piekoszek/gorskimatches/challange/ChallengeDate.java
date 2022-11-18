package pl.piekoszek.gorskimatches.challange;

import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class ChallengeDate {
    Date currentDate() {
        return new Date();
    }
}
