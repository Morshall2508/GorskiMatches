package pl.piekoszek.gorskimatches.equation;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CharacterChangeInStringTest {

    private final CharacterChangeInString characterChangeInString = new CharacterChangeInString();

    @Test
    void shouldChangeTheCharcterInString() {
        assertThat(characterChangeInString.changeCharactersInString("6+1=7",0,'0'))
                .isEqualTo("0+1=7");
        assertThat(characterChangeInString.changeCharactersInString("8+1=7",0,'0'))
                .isEqualTo("0+1=7");
        assertThat(characterChangeInString.changeCharactersInString("6+1=5",4,'3'))
                .isEqualTo("6+1=3");
        assertThat(characterChangeInString.changeCharactersInString("6+1=7",0,'0'))
                .isEqualTo("0+1=7");

    }
}







