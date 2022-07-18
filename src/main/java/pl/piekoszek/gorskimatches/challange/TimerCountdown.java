package pl.piekoszek.gorskimatches.challange;

import org.springframework.stereotype.Component;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import static java.util.concurrent.TimeUnit.SECONDS;

@Component
public class TimerCountdown {
    final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    final Runnable runnable = new Runnable() {
        int countdownStarter = 30;
        @Override
        public void run() {

            System.out.println(countdownStarter);
            countdownStarter--;

            if (countdownStarter < 0) {
                System.out.println("Time's Up!");
                scheduler.shutdown();
            }
            scheduler.scheduleAtFixedRate(runnable, 0, 1, SECONDS);
        }
    };
}

