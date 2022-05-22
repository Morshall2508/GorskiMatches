package pl.piekoszek.gorskimatches.equation;

import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
class EquationMathChecker {
    EquationGenerator equationGenerator = new EquationGenerator();

    public ArrayList EquationGenerator() {
        var equations = new ArrayList();
        var coorectEquations = new ArrayList();
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                for (int k = 0; k < 10; k++) {
                    equations.add(i + "+" + j + "=" + k);
                    equations.add(i + "-" + j + "=" + k);
                    for (int l = 0; l < equations.size(); l++) {
                        coorectEquations.add((isMathematicallyCorrect((String) equations.get(l))));
                    }
                }
            }
        }
        return coorectEquations;
    }

    boolean isMathematicallyCorrect(String equation) {

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


