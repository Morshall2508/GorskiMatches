package pl.piekoszek.gorskimatches.challange;

import org.springframework.stereotype.Component;

@Component
class TimeService {
    long millisSinceEpoch (){
        return System.currentTimeMillis();
    }
}
