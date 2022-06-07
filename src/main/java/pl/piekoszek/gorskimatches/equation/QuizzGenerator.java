package pl.piekoszek.gorskimatches.equation;

import org.springframework.stereotype.Component;

@Component
public class QuizzGenerator {

    private final EquationGenerator equationGenerator;

    public QuizzGenerator(EquationGenerator equationGenerator) {
        this.equationGenerator = equationGenerator;
    }
}
