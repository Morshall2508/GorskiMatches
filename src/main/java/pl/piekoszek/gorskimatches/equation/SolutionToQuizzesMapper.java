package pl.piekoszek.gorskimatches.equation;

import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class SolutionToQuizzesMapper {
    private final CharacterChanger characterChanger;
    private final EquationMathChecker equationMathChecker;
    private final Map<Character, List<Character>> numberOrSymbolChangeableToOther = new HashMap<>();
    private final Map<Character, List<Character>> takeOneMatchFromNumberOrSymbol = new HashMap<>();
    private final Map<Character, List<Character>> giveOneMatchFromNumberOrSymbol = new HashMap<>();

    SolutionToQuizzesMapper(CharacterChanger characterChanger, EquationMathChecker equationMathChecker) {
        this.characterChanger = characterChanger;
        this.equationMathChecker = equationMathChecker;
    }

    {
        numberOrSymbolChangeableToOther.put('0', new ArrayList<>());
        numberOrSymbolChangeableToOther.put('1', new ArrayList<>());
        numberOrSymbolChangeableToOther.put('2', new ArrayList<>());
        numberOrSymbolChangeableToOther.put('3', new ArrayList<>());
        numberOrSymbolChangeableToOther.put('4', new ArrayList<>());
        numberOrSymbolChangeableToOther.put('5', new ArrayList<>());
        numberOrSymbolChangeableToOther.put('6', new ArrayList<>());
        numberOrSymbolChangeableToOther.put('7', new ArrayList<>());
        numberOrSymbolChangeableToOther.put('8', new ArrayList<>());
        numberOrSymbolChangeableToOther.put('9', new ArrayList<>());
        numberOrSymbolChangeableToOther.put('+', new ArrayList<>());
        numberOrSymbolChangeableToOther.put('-', new ArrayList<>());
        numberOrSymbolChangeableToOther.put('=', new ArrayList<>());
        numberOrSymbolChangeableToOther.get('0').add('6');
        numberOrSymbolChangeableToOther.get('0').add('9');
        numberOrSymbolChangeableToOther.get('6').add('0');
        numberOrSymbolChangeableToOther.get('6').add('9');
        numberOrSymbolChangeableToOther.get('9').add('0');
        numberOrSymbolChangeableToOther.get('9').add('6');
        numberOrSymbolChangeableToOther.get('3').add('2');
        numberOrSymbolChangeableToOther.get('3').add('5');
        numberOrSymbolChangeableToOther.get('5').add('3');
        numberOrSymbolChangeableToOther.get('2').add('3');
    }

    {
        takeOneMatchFromNumberOrSymbol.put('0', new ArrayList<>());
        takeOneMatchFromNumberOrSymbol.put('1', new ArrayList<>());
        takeOneMatchFromNumberOrSymbol.put('2', new ArrayList<>());
        takeOneMatchFromNumberOrSymbol.put('3', new ArrayList<>());
        takeOneMatchFromNumberOrSymbol.put('4', new ArrayList<>());
        takeOneMatchFromNumberOrSymbol.put('5', new ArrayList<>());
        takeOneMatchFromNumberOrSymbol.put('6', new ArrayList<>());
        takeOneMatchFromNumberOrSymbol.put('7', new ArrayList<>());
        takeOneMatchFromNumberOrSymbol.put('8', new ArrayList<>());
        takeOneMatchFromNumberOrSymbol.put('9', new ArrayList<>());
        takeOneMatchFromNumberOrSymbol.put('+', new ArrayList<>());
        takeOneMatchFromNumberOrSymbol.put('-', new ArrayList<>());
        takeOneMatchFromNumberOrSymbol.put('=', new ArrayList<>());
        takeOneMatchFromNumberOrSymbol.get('6').add('5');
        takeOneMatchFromNumberOrSymbol.get('7').add('1');
        takeOneMatchFromNumberOrSymbol.get('8').add('0');
        takeOneMatchFromNumberOrSymbol.get('8').add('6');
        takeOneMatchFromNumberOrSymbol.get('8').add('9');
        takeOneMatchFromNumberOrSymbol.get('9').add('5');
        takeOneMatchFromNumberOrSymbol.get('+').add('-');
    }

    {
        giveOneMatchFromNumberOrSymbol.put('0', new ArrayList<>());
        giveOneMatchFromNumberOrSymbol.put('1', new ArrayList<>());
        giveOneMatchFromNumberOrSymbol.put('2', new ArrayList<>());
        giveOneMatchFromNumberOrSymbol.put('3', new ArrayList<>());
        giveOneMatchFromNumberOrSymbol.put('4', new ArrayList<>());
        giveOneMatchFromNumberOrSymbol.put('5', new ArrayList<>());
        giveOneMatchFromNumberOrSymbol.put('6', new ArrayList<>());
        giveOneMatchFromNumberOrSymbol.put('7', new ArrayList<>());
        giveOneMatchFromNumberOrSymbol.put('8', new ArrayList<>());
        giveOneMatchFromNumberOrSymbol.put('9', new ArrayList<>());
        giveOneMatchFromNumberOrSymbol.put('+', new ArrayList<>());
        giveOneMatchFromNumberOrSymbol.put('-', new ArrayList<>());
        giveOneMatchFromNumberOrSymbol.put('=', new ArrayList<>());
        giveOneMatchFromNumberOrSymbol.get('0').add('8');
        giveOneMatchFromNumberOrSymbol.get('1').add('7');
        giveOneMatchFromNumberOrSymbol.get('3').add('9');
        giveOneMatchFromNumberOrSymbol.get('5').add('6');
        giveOneMatchFromNumberOrSymbol.get('5').add('9');
        giveOneMatchFromNumberOrSymbol.get('6').add('8');
        giveOneMatchFromNumberOrSymbol.get('9').add('8');
        giveOneMatchFromNumberOrSymbol.get('-').add('+');
    }

    public Map<String, Set<String>> insideSingleNumber(String solution) {
        Map<String, Set<String>> quizzesAndSolutions = new HashMap<>();
        for (int i = 0; i < solution.length(); i++) {
            char numberOrSymbolToBeReplaced = solution.charAt(i);
            var numbersAndSymbolsToBeChangedTo = numberOrSymbolChangeableToOther.get(numberOrSymbolToBeReplaced);

            for (Character replacement : numbersAndSymbolsToBeChangedTo) {
                String quiz = characterChanger.changeCharactersInString(solution, i, replacement);
                quizzesAndSolutions.put(quiz, new HashSet<>());
                quizzesAndSolutions.get(quiz).add(solution);
            }
        }
        return quizzesAndSolutions;
    }

    public Map<String, Set<String>> insideEquation(String solution) {
        Map<String, Set<String>> quizzesAndSolutionsWithinEquation = new HashMap<>();

        for (int i = 0; i < solution.length(); i++) {
            char numberOrSymbolToBeReplaced = solution.charAt(i);
            var numbersAndSymbolsToBeTakenFrom = takeOneMatchFromNumberOrSymbol.get(numberOrSymbolToBeReplaced);

            for (Character numberWithTakenMatch : numbersAndSymbolsToBeTakenFrom) {
                String quizWithTakenMatch = characterChanger.changeCharactersInString(solution, i, numberWithTakenMatch);
                for (int k = 0; k < quizWithTakenMatch.length(); k++) {
                    if (k != i) {
                        char numberOrSymbolToAddMatchTo = quizWithTakenMatch.charAt(k);
                        var numbersAndSymbolsToBeAddedTo = giveOneMatchFromNumberOrSymbol.get(numberOrSymbolToAddMatchTo);
                        for (Character matchReceiver : numbersAndSymbolsToBeAddedTo) {
                            String quizWithGivenMatch = characterChanger.changeCharactersInString(quizWithTakenMatch, k, matchReceiver);
                            quizzesAndSolutionsWithinEquation.put(quizWithGivenMatch, new HashSet<>());
                            quizzesAndSolutionsWithinEquation.get(quizWithGivenMatch).add(solution);
                            quizzesAndSolutionsWithinEquation.remove(solution);
                            if (equationMathChecker.isMathematicallyCorrect(quizWithGivenMatch)) {
                                quizzesAndSolutionsWithinEquation.remove(quizWithGivenMatch);
                            }
                        }
                    }
                }
            }
        }
        return quizzesAndSolutionsWithinEquation;
    }
}
