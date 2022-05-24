package pl.piekoszek.gorskimatches.equation;

import org.springframework.stereotype.Component;
import java.util.*;

@Component
public class EquationGenerator {

    List<String> listOfMathCorrectEquations = new ArrayList<>();

    EquationGenerator(EquationMathChecker equationMathChecker) {
        Map<String, Boolean> allEquations = new HashMap<>();
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
            allEquations.put(equation, equationMathChecker.isMathematicallyCorrect(equation));
            allEquations.remove(equation, false);
        }
        Set<String> keySet = allEquations.keySet();
        listOfMathCorrectEquations = new ArrayList<>(keySet);
    }
}
