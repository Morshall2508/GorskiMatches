package pl.piekoszek.gorskimatches.equation;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SolutionToQuizzesMapperTest {

    private final SolutionToQuizzesMapper solutionToQuizzesMapper = new SolutionToQuizzesMapper();

    @Test
    void shouldCreateQuizzesFromSolutionByMovingMatchInsideOneCharacter() {
        assertThat(solutionToQuizzesMapper.insideSingleMatch("6+1=7"))
                .containsExactlyInAnyOrder("0+1=7", "9+1=7");
    }

    @Test
    void shouldCreateQuizzesFromSolutionByMovingMatchInsideOneCharacter2() {
        assertThat(solutionToQuizzesMapper.insideSingleMatch("3+1=4"))
                .containsExactlyInAnyOrder("2+1=4", "5+1=4");
    }

    @Test
    void shouldCreateQuizzesFromSolutionByMovingMatchInsideOneCharacter3() {
        assertThat(solutionToQuizzesMapper.insideSingleMatch("0+1=1"))
                .containsExactlyInAnyOrder("6+1=1", "9+1=1");
    }
}

