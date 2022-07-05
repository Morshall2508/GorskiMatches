package pl.piekoszek.gorskimatches.equation;

import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class SolutionToQuizzesMapperTest {

    private final SolutionToQuizzesMapper solutionToQuizzesMapper = new SolutionToQuizzesMapper(new CharacterChanger());

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

        assertThat(quizSolutionMap.get("9-3=8")).containsExactly("5+3=8");
        assertThat(quizSolutionMap.get("6-3=8")).containsExactly("5+3=8");
        assertThat(quizSolutionMap.get("5-9=8")).containsExactly("5+3=8");
        assertThat(quizSolutionMap.get("9+3=0")).containsExactly("5+3=8");
        assertThat(quizSolutionMap.get("6+3=0")).containsExactly("5+3=8");
        assertThat(quizSolutionMap.get("5+9=0")).containsExactly("5+3=8");
        assertThat(quizSolutionMap.get("9+3=9")).containsExactly("5+3=8");
        assertThat(quizSolutionMap.get("6+3=9")).containsExactly("5+3=8");
        assertThat(quizSolutionMap.get("5+9=9")).containsExactly("5+3=8");
        assertThat(quizSolutionMap.get("9+3=6")).containsExactly("5+3=8");
        assertThat(quizSolutionMap.get("6+3=6")).containsExactly("5+3=8");
        assertThat(quizSolutionMap.get("5+9=6")).containsExactly("5+3=8");

    }
}