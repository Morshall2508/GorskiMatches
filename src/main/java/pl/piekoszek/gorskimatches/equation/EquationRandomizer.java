package pl.piekoszek.gorskimatches.equation;

import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class EquationRandomizer {
    EquationGenerator equationGenerator = new EquationGenerator(null, null);

    public String randomEquation() {
        Map<String, Set<String>> allQuizzesAndSolutions = equationGenerator.getAllQuizzesAndSolutions();
        Random generator = new Random();
        Object[] values = allQuizzesAndSolutions.values().toArray();
        return (String) values[generator.nextInt(values.length)];
    }
}