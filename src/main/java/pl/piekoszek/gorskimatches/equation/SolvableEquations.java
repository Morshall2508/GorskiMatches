package pl.piekoszek.gorskimatches.equation;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class SolvableEquations {

    Map<String, String> storageOfSolvableEquations = new HashMap<>();

    {

        storageOfSolvableEquations.put("9+2=6", "8-2=6");
        storageOfSolvableEquations.put("6-4=3", "6-4=2");
        storageOfSolvableEquations.put("5+7=2", "9-7=2");
        storageOfSolvableEquations.put("4+6=4", "0+4=4");
        storageOfSolvableEquations.put("5+2=8", "5+3=8");
        storageOfSolvableEquations.put("1+2=8", "7+2=9");
        storageOfSolvableEquations.put("7-2=4", "7-3=4");

    }

    public boolean checkForCorrectAnswer(String quiz, String solution) {
        var correctSolution = storageOfSolvableEquations.get(quiz);
        return correctSolution.equals(solution);
    }
}

