package pl.piekoszek.gorskimatches.equation;

import org.springframework.stereotype.Component;

@Component
class EquationMathChecker {


    boolean isMathematicallyCorrect(String equation) {

        char[] equationChar = equation.toCharArray();

        int a = Integer.parseInt(String.valueOf(equationChar[0]));
        int b = Integer.parseInt(String.valueOf(equationChar[2]));
        int c = Integer.parseInt(String.valueOf(equationChar[4]));

        if (equation.contains("+")) {
            if (a + b == c) {
                return true;
            }
        } else if (equation.contains("-")) {
            if (a - b == c) {
                return true;
            }
        }
        return false;
    }

}


