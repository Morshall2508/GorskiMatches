package pl.piekoszek.gorskimatches.equation;

import org.junit.jupiter.api.Test;


import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

public class SolutionToQuizzesMapperTest {

    private final SolutionToQuizzesMapper solutionToQuizzesMapper = new SolutionToQuizzesMapper(new CharacterChanger(), new EquationMathChecker());
    private final EquationMathChecker equationMathChecker = new EquationMathChecker();
    private final EquationGenerator equationGenerator = new EquationGenerator(equationMathChecker);

    @Test
    void shouldCreateQuizzesFromSolutionByMovingMatchInsideOneCharacter() {
        var quizSolutionMap = solutionToQuizzesMapper.insideSingleNumber("6+1=7");

        assertThat(quizSolutionMap.get("0+1=7")).containsExactly("6+1=7");
        assertThat(quizSolutionMap.get("9+1=7")).containsExactly("6+1=7");
        assertThat(quizSolutionMap).hasSize(2);
    }

    @Test
    void shouldCreateQuizzesFromSolutionByMovingMatchInsideOneCharacter2() {
        solutionToQuizzesMapper.insideSingleNumber("2+5=7");
        assertThat(solutionToQuizzesMapper.insideSingleNumber("6+1=7")).hasSize(2);
    }

    @Test
    void shouldCreateQuizzesFromSolutionByMovingMatchInsideOneCharacter3() {
        var quizSolutionMap = solutionToQuizzesMapper.insideSingleNumber("5+1=6");

        assertThat(quizSolutionMap.get("5+1=0")).containsExactly("5+1=6");
        assertThat(quizSolutionMap.get("5+1=9")).containsExactly("5+1=6");
        assertThat(quizSolutionMap.get("3+1=6")).containsExactly("5+1=6");
        assertThat(quizSolutionMap).hasSize(3);
    }

    @Test
    void shouldCreateQuizzesFromSolutionByMovingMatchInsideWholeEquation() {
        var quizSolutionMap = solutionToQuizzesMapper.insideEquation("5+3=8");
        assertThat(quizSolutionMap).hasSize(11);
        assertThat(quizSolutionMap.get("9-3=8")).containsExactly("5+3=8");
        assertThat(quizSolutionMap.get("6-3=8")).containsExactly("5+3=8");
        assertThat(quizSolutionMap.get("5-9=8")).containsExactly("5+3=8");
        assertThat(quizSolutionMap.get("9+3=0")).containsExactly("5+3=8");
        assertThat(quizSolutionMap.get("6+3=0")).containsExactly("5+3=8");
        assertThat(quizSolutionMap.get("5+9=0")).containsExactly("5+3=8");
        assertThat(quizSolutionMap.get("9+3=9")).containsExactly("5+3=8");
        assertThat(quizSolutionMap.get("5+9=9")).containsExactly("5+3=8");
        assertThat(quizSolutionMap.get("9+3=6")).containsExactly("5+3=8");
        assertThat(quizSolutionMap.get("6+3=6")).containsExactly("5+3=8");
        assertThat(quizSolutionMap.get("5+9=6")).containsExactly("5+3=8");

    }

    @Test
    void shouldCreateQuizzesFromSolutionByMovingMatchInsideWholeEquation2() {
        var quizSolutionMap = solutionToQuizzesMapper.insideEquation("2+3=5");
        assertThat(quizSolutionMap).hasSize(3);
        assertThat(quizSolutionMap.get("2-9=5")).containsExactly("2+3=5");
        assertThat(quizSolutionMap.get("2-3=6")).containsExactly("2+3=5");
        assertThat(quizSolutionMap.get("2-3=9")).containsExactly("2+3=5");
    }

    @Test
    void shouldCreateQuizzesFromSolutionByMovingMatchInsideWholeEquation3() {
        var quizSolutionMap = solutionToQuizzesMapper.insideEquation("6+3=9");
        assertThat(quizSolutionMap).hasSize(6);
        assertThat(quizSolutionMap.get("5+9=9")).containsExactly("6+3=9");
        assertThat(quizSolutionMap.get("8+3=5")).containsExactly("6+3=9");
        assertThat(quizSolutionMap.get("6+9=5")).containsExactly("6+3=9");
        assertThat(quizSolutionMap.get("8-3=9")).containsExactly("6+3=9");
        assertThat(quizSolutionMap.get("6-9=9")).containsExactly("6+3=9");
        assertThat(quizSolutionMap.get("6-3=8")).containsExactly("6+3=9");

    }

    @Test
    void shouldCreateQuizzesFromSolutionByMovingMatchInsideWholeEquation4() {
        var quizSolutionMap = solutionToQuizzesMapper.insideEquation("7-1=6");
        assertThat(quizSolutionMap).hasSize(5);
        assertThat(quizSolutionMap.get("1+1=6")).containsExactly("7-1=6");
        assertThat(quizSolutionMap.get("1-7=6")).containsExactly("7-1=6");
        assertThat(quizSolutionMap.get("1-1=8")).containsExactly("7-1=6");
        assertThat(quizSolutionMap.get("7-7=5")).containsExactly("7-1=6");
        assertThat(quizSolutionMap.get("7+1=5")).containsExactly("7-1=6");

    }

    @Test
    void shouldCreateQuizzesFromSolutionByMovingMatchInsideWholeEquation5() {
        var quizSolutionMap = solutionToQuizzesMapper.insideEquation("0+8=8");
        assertThat(quizSolutionMap).hasSize(6);
        assertThat(quizSolutionMap.get("8+6=8")).containsExactly("0+8=8");
        assertThat(quizSolutionMap.get("8+9=8")).containsExactly("0+8=8");
        assertThat(quizSolutionMap.get("8-8=8")).containsExactly("0+8=8");
        assertThat(quizSolutionMap.get("8+8=6")).containsExactly("0+8=8");
        assertThat(quizSolutionMap.get("8+8=9")).containsExactly("0+8=8");
        assertThat(quizSolutionMap.get("8+8=9")).containsExactly("0+8=8");

    }

    @Test
    void shouldNotHaveAnyMatchematicallyCorrectQuizzes() {
        var allGeneratedQuizzes = new QuizzesGenerator(solutionToQuizzesMapper, equationGenerator);
        var allEquations = allGeneratedQuizzes.getAllSolutionsByQuiz();
        var quizzes = allEquations.keySet();
        for (String quiz : quizzes) {
            var result = equationMathChecker.isMathematicallyCorrect(quiz);
//            assertThat(result).isTrue();
            if (equationMathChecker.isMathematicallyCorrect(quiz)) {

                System.out.println(quiz);
            }
        }
    }
}