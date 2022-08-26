package pl.piekoszek.gorskimatches.validation;

import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
public class PatternMatches {

    public boolean checkIfPatternIsCorrect(String email, String regexPattern) {
        return Pattern.compile(regexPattern)
                .matcher(email)
                .matches();
    }

    public String removeSpaces(String answer) {
        return answer.trim().replaceAll(" ", "");
    }

}
