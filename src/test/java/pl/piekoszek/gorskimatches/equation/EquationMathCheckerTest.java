package pl.piekoszek.gorskimatches.equation;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class EquationMathCheckerTest {

    private final EquationMathChecker equationMathChecker = new EquationMathChecker();

    @Test
    void shouldReturnTrueOnCorrectAddEquations() {
        assertThat(equationMathChecker.isMathematicallyCorrect("1+2=3")).isTrue();
        assertThat(equationMathChecker.isMathematicallyCorrect("0+0=0")).isTrue();
        assertThat(equationMathChecker.isMathematicallyCorrect("9+0=9")).isTrue();
        assertThat(equationMathChecker.isMathematicallyCorrect("0+9=9")).isTrue();
        assertThat(equationMathChecker.isMathematicallyCorrect("4+4=8")).isTrue();
        assertThat(equationMathChecker.isMathematicallyCorrect("3+3=6")).isTrue();
    }

    @Test
    void shouldReturnTrueOnCorrectSubtractEquations() {
        assertThat(equationMathChecker.isMathematicallyCorrect("0-0=0")).isTrue();
        assertThat(equationMathChecker.isMathematicallyCorrect("5-2=3")).isTrue();
        assertThat(equationMathChecker.isMathematicallyCorrect("4-1=3")).isTrue();
        assertThat(equationMathChecker.isMathematicallyCorrect("9-9=0")).isTrue();
        assertThat(equationMathChecker.isMathematicallyCorrect("4-4=0")).isTrue();
        assertThat(equationMathChecker.isMathematicallyCorrect("7-2=5")).isTrue();
    }

    @Test
    void shouldReturnFalseOnIncorrectAddEquations() {
        assertThat(equationMathChecker.isMathematicallyCorrect("1+2=6")).isFalse();
        assertThat(equationMathChecker.isMathematicallyCorrect("2+2=0")).isFalse();
        assertThat(equationMathChecker.isMathematicallyCorrect("9+0=3")).isFalse();
        assertThat(equationMathChecker.isMathematicallyCorrect("0+9=5")).isFalse();
        assertThat(equationMathChecker.isMathematicallyCorrect("4+4=7")).isFalse();
        assertThat(equationMathChecker.isMathematicallyCorrect("3+3=4")).isFalse();
    }

    @Test
    void shouldReturnFalseOnIncorrectSubtractEquations() {
        assertThat(equationMathChecker.isMathematicallyCorrect("1-2=0")).isFalse();
        assertThat(equationMathChecker.isMathematicallyCorrect("0-0=1")).isFalse();
        assertThat(equationMathChecker.isMathematicallyCorrect("9-0=4")).isFalse();
        assertThat(equationMathChecker.isMathematicallyCorrect("0-9=3")).isFalse();
        assertThat(equationMathChecker.isMathematicallyCorrect("4-4=2")).isFalse();
        assertThat(equationMathChecker.isMathematicallyCorrect("3-3=7")).isFalse();
    }

}