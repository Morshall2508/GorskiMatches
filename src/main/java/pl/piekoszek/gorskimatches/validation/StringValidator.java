package pl.piekoszek.gorskimatches.validation;

import org.springframework.stereotype.Component;

@Component
public class StringValidator {
    public String removeSpaces(String answer) {
        return answer.trim().replaceAll(" ", "");
    }
}
