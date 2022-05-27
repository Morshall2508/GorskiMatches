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
}


