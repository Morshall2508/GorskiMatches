package pl.piekoszek.gorskimatches.equation;

import org.springframework.stereotype.Component;

@Component
public class CharacterChangeInString {
public String changeCharactersInString(String inputString, int a, Object x) {
        String outputString;
        char[] inputStringCharArr = inputString.toCharArray();
        inputStringCharArr[a] = (char) x;
        outputString = String.valueOf(inputStringCharArr);
        return outputString;
    }
}
