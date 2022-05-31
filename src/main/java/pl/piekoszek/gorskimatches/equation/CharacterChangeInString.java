package pl.piekoszek.gorskimatches.equation;

import org.springframework.stereotype.Component;

@Component
public class CharacterChangeInString {
    public String changeCharactersInString(String providedSolution, int positionInString, char numberToBeChangedTo) {
        String solutionToQuiz;
        char[] inputStringCharArr = providedSolution.toCharArray();
        inputStringCharArr[positionInString] = numberToBeChangedTo;
        solutionToQuiz = String.valueOf(inputStringCharArr);
        return solutionToQuiz;
    }
}
