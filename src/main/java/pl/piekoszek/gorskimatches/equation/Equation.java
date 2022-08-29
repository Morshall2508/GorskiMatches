package pl.piekoszek.gorskimatches.equation;

import javax.validation.constraints.NotBlank;

public class Equation {
    public String quiz;

//    @NotBlank(message = "Answer has to be provided")
    public String answer;

    public Equation(String quiz, String answer) {
        this.quiz = quiz;
        this.answer = answer;
    }

    public Equation() {

    }
}

