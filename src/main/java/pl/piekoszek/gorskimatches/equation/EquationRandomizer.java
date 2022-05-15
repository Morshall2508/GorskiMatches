package pl.piekoszek.gorskimatches.equation;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class EquationRandomizer {

    public String randomEquation() {
        String[] randomEquations = {"9+2=6", "6-4=3", "5+7=2", "4+6=4", "5+2=8", "1+2=8", "7-2=4"};
        int positionInString = new Random().nextInt(randomEquations.length);
        return randomEquations[positionInString];
    }
}
