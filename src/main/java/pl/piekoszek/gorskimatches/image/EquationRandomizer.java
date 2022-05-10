package pl.piekoszek.gorskimatches.image;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class EquationRandomizer {
    public String[] randomEquations = {"9+2=6", "5+7=2", "9+3=5", "4+6=4", "5+2=8", "1+2=8", "3+2=4", "7-2=4"};
    int positionInString = new Random().nextInt(randomEquations.length);
    String randomNumber = (randomEquations[positionInString]);
}