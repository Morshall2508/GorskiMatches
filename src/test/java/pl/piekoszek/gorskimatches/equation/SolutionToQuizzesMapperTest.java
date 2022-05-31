package pl.piekoszek.gorskimatches.equation;

import org.junit.jupiter.api.Test;

import java.util.HashSet;

import static org.assertj.core.api.Assertions.assertThat;

public class SolutionToQuizzesMapperTest {

    private final SolutionToQuizzesMapper solutionToQuizzesMapper = new SolutionToQuizzesMapper();

    @Test
    void shouldCreateQuizzesFromSolutionByMovingMatchInsideOneCharacter() {
        assertThat(solutionToQuizzesMapper.insideSingleMatch("6+1=7"))
                .containsKeys("9+1=7");
    }

    @Test
    void shouldCreateQuizzesFromSolutionByMovingMatchInsideOneCharacter2() {
        assertThat(solutionToQuizzesMapper.insideSingleMatch("5+1=6"))
                .containsKeys("3+1=6");
    }

    @Test
    void shouldCreateQuizzesFromSolutionByMovingMatchInsideOneCharacter3() {
        assertThat(solutionToQuizzesMapper.insideSingleMatch("5+1=6"))
                .containsKeys("5+1=0");

    }

    @Test
    void shouldCreateQuizzesFromSolutionByMovingMatchInsideOneCharacter4() {
        assertThat(solutionToQuizzesMapper.insideSingleMatch("5+1=6"))
                .containsKeys("5+1=9");

    }
}