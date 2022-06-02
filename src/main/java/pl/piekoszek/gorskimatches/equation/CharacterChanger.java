package pl.piekoszek.gorskimatches.equation;

import org.springframework.stereotype.Component;

@Component
public class CharacterChanger {
    public String changeCharactersInString(String string, int position, char character) {
        char[] inputStringCharArr = string.toCharArray();
        inputStringCharArr[position] = character;
        return String.valueOf(inputStringCharArr);
    }
}
