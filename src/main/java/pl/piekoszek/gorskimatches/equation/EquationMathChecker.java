package pl.piekoszek.gorskimatches.equation;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Component
class EquationMathChecker {

    Map<String, Boolean> allEquations = new HashMap<>();
    ArrayList equations = new ArrayList();

    {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                for (int k = 0; k < 10; k++) {
                    equations.add(i + "+" + j + "=" + k);
                    equations.add(i + "-" + j + "=" + k);
                    for (int l = 0; l < equations.size(); l++) {
                        allEquations.put((String) equations.get(l), isMathematicallyCorrect((String) equations.get(l)));
                        allEquations.remove(equations.get(l), false);

                    }
                }
            }
        }
        Set<String> keySet = allEquations.keySet();
        ArrayList<String> listOfMathCorrEquations = new ArrayList<String>(keySet);
    }

    static boolean isMathematicallyCorrect(String equation) {

        char[] equationChar = equation.toCharArray();

        int a = Integer.parseInt(String.valueOf(equationChar[0]));
        int b = Integer.parseInt(String.valueOf(equationChar[2]));
        int c = Integer.parseInt(String.valueOf(equationChar[4]));

        if (equation.contains("+")) {
            return a + b == c;
        } else if (equation.contains("-")) {
            return a - b == c;
        }
        return false;
    }
}


