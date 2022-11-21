package pl.piekoszek.gorskimatches.equation;

import org.springframework.stereotype.Component;

@Component
class CharacterChanger {
    public String changeCharactersInString(String string, int position, char characters) {
        char[] inputStringCharArr = string.toCharArray();
        inputStringCharArr[position] = characters;
        return String.valueOf(inputStringCharArr);
    }
}
