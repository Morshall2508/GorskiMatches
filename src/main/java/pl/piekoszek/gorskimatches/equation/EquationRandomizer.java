package pl.piekoszek.gorskimatches.equation;

import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class EquationRandomizer {

    private final EquationGenerator equationGenerator;

    public EquationRandomizer(EquationGenerator equationGenerator) {
        this.equationGenerator = equationGenerator;
    }

    public String randomEquation() {
        Map<String, Set<String>> allQuizzesAndSolutions = equationGenerator.getAllQuizzesAndSolutions();
        Object[] quizSetForRandomizing = allQuizzesAndSolutions.keySet().toArray();
        Random generator = new Random();
        Object randomIndex = quizSetForRandomizing[generator.nextInt(quizSetForRandomizing.length)];
        return String.valueOf(randomIndex);
    }
}