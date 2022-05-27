package pl.piekoszek.gorskimatches.equation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SolutionToQuizzesMapper {
    private final CharacterChangeInString characterChangeInString = new CharacterChangeInString();
    Map<Character, List<Character>> numberChangeableToOther = new HashMap<Character, List<Character>>();

    {
        numberChangeableToOther.put('0', new ArrayList<Character>());
        numberChangeableToOther.put('1', new ArrayList<Character>());
        numberChangeableToOther.put('2', new ArrayList<Character>());
        numberChangeableToOther.put('3', new ArrayList<Character>());
        numberChangeableToOther.put('4', new ArrayList<Character>());
        numberChangeableToOther.put('5', new ArrayList<Character>());
        numberChangeableToOther.put('6', new ArrayList<Character>());
        numberChangeableToOther.put('7', new ArrayList<Character>());
        numberChangeableToOther.put('8', new ArrayList<Character>());
        numberChangeableToOther.put('9', new ArrayList<Character>());
        numberChangeableToOther.get('0').add('6');
        numberChangeableToOther.get('0').add('9');
        numberChangeableToOther.get('6').add('0');
        numberChangeableToOther.get('6').add('9');
        numberChangeableToOther.get('9').add('0');
        numberChangeableToOther.get('9').add('6');
        numberChangeableToOther.get('3').add('5');
        numberChangeableToOther.get('3').add('2');
        numberChangeableToOther.get('5').add('3');
        numberChangeableToOther.get('2').add('3');
    }

    public List<String> insideSingleMatch(String solution) {
        List<String> quizEquationsSingleNumber = new ArrayList<>();
        String quiz = null;
        for (int i = 0; i < solution.length(); i++) {
            quiz = characterChangeInString.changeCharactersInString(solution, i, numberChangeableToOther.get(i));
            quizEquationsSingleNumber.add(quiz);
        }
        return quizEquationsSingleNumber;
    }


}
