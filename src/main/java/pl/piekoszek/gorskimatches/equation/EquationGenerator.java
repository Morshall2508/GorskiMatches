package pl.piekoszek.gorskimatches.equation;

import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class EquationGenerator {

    private final EquationMathChecker equationMathChecker;

    public EquationGenerator(EquationMathChecker equationMathChecker) {
        this.equationMathChecker = equationMathChecker;
    }

    public Set<String> mathematicallyCorrectEquations() {

        Map<String, Boolean> correctEquations = new HashMap<>();
        List<String> equations = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                for (int k = 0; k < 10; k++) {
                    equations.add(i + "+" + j + "=" + k);
                    equations.add(i + "-" + j + "=" + k);
                }
            }
        }
        for (String equation : equations) {
            correctEquations.put(equation, equationMathChecker.isMathematicallyCorrect(equation));
            correctEquations.remove(equation, false);
        }
        return correctEquations.keySet();
    }

}
