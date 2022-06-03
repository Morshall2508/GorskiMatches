package pl.piekoszek.gorskimatches.equation;

import java.util.*;

public class SolutionToQuizzesMapper {
    private CharacterChanger characterChangeInString = new CharacterChanger();
    private final Map<Character, List<Character>> numberOrSymbolChangeableToOther = new HashMap<>();
    SolutionToQuizzesMapper(CharacterChanger characterChanger) {
        this.characterChangeInString = characterChanger;
    }

    {
        numberOrSymbolChangeableToOther.put('0', new ArrayList<>());
        numberOrSymbolChangeableToOther.put('1', new ArrayList<>());
        numberOrSymbolChangeableToOther.put('2', new ArrayList<>());
        numberOrSymbolChangeableToOther.put('3', new ArrayList<>());
        numberOrSymbolChangeableToOther.put('5', new ArrayList<>());
        numberOrSymbolChangeableToOther.put('6', new ArrayList<>());
        numberOrSymbolChangeableToOther.put('7', new ArrayList<>());
        numberOrSymbolChangeableToOther.put('8', new ArrayList<>());
        numberOrSymbolChangeableToOther.put('9', new ArrayList<>());
        numberOrSymbolChangeableToOther.put('+', new ArrayList<>());
        numberOrSymbolChangeableToOther.put('-', new ArrayList<>());
        numberOrSymbolChangeableToOther.put('=', new ArrayList<>());
        numberOrSymbolChangeableToOther.put('4', new ArrayList<>());
        numberOrSymbolChangeableToOther.get('0').add('6');
        numberOrSymbolChangeableToOther.get('0').add('9');
        numberOrSymbolChangeableToOther.get('6').add('0');
        numberOrSymbolChangeableToOther.get('6').add('9');
        numberOrSymbolChangeableToOther.get('9').add('0');
        numberOrSymbolChangeableToOther.get('9').add('6');
        numberOrSymbolChangeableToOther.get('3').add('5');
        numberOrSymbolChangeableToOther.get('3').add('2');
        numberOrSymbolChangeableToOther.get('5').add('3');
        numberOrSymbolChangeableToOther.get('2').add('3');
    }

    public Map<String, Set<String>> insideSingleMatch(String solution) {
        Map<String, Set<String>> quizzesAndSolutions = new HashMap<>();
        for (int i = 0; i < solution.length(); i++) {
            char numberOrSymbolToBeReplaced = solution.charAt(i);
            var numbersAndSymbolsToBeChangedTo = numberOrSymbolChangeableToOther.get(numberOrSymbolToBeReplaced);

            for (Character replacement : numbersAndSymbolsToBeChangedTo) {
                String quiz = characterChangeInString.changeCharactersInString(solution, i, replacement);
                quizzesAndSolutions.put(quiz, new HashSet<>());
                quizzesAndSolutions.get(quiz).add(solution);
            }
        }
        return quizzesAndSolutions;
    }
}
