package pl.piekoszek.gorskimatches.equation;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class Equation {
    public String quiz;

    @NotBlank(message = "Answer has to be provided")
    @Valid
    @Pattern(regexp = "\\b\\d[+-]\\d[=]\\d\\b", message = "Incorrect answer format")
    public String answer;

    public Equation(String quiz, String answer) {
        this.quiz = quiz;
        this.answer = answer;
    }

    public Equation() {

    }
}

