package pl.piekoszek.gorskimatches.equation;

import org.springframework.stereotype.Component;

import java.util.*;

@Component
class SolutionToQuizzesMapper {
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
        numberOrSymbolChangeableToOther.put('0', List.of('6', '9'));
        numberOrSymbolChangeableToOther.put('1', Collections.emptyList());
        numberOrSymbolChangeableToOther.put('2', List.of('3'));
        numberOrSymbolChangeableToOther.put('3', List.of('2', '5'));
        numberOrSymbolChangeableToOther.put('4', Collections.emptyList());
        numberOrSymbolChangeableToOther.put('5', List.of('3'));
        numberOrSymbolChangeableToOther.put('6', List.of('0', '9'));
        numberOrSymbolChangeableToOther.put('7', Collections.emptyList());
        numberOrSymbolChangeableToOther.put('8', Collections.emptyList());
        numberOrSymbolChangeableToOther.put('9', List.of('0', '6'));
        numberOrSymbolChangeableToOther.put('+', Collections.emptyList());
        numberOrSymbolChangeableToOther.put('-', Collections.emptyList());
        numberOrSymbolChangeableToOther.put('=', Collections.emptyList());
    }

    {
        takeOneMatchFromNumberOrSymbol.put('0', Collections.emptyList());
        takeOneMatchFromNumberOrSymbol.put('1', Collections.emptyList());
        takeOneMatchFromNumberOrSymbol.put('2', Collections.emptyList());
        takeOneMatchFromNumberOrSymbol.put('3', Collections.emptyList());
        takeOneMatchFromNumberOrSymbol.put('4', Collections.emptyList());
        takeOneMatchFromNumberOrSymbol.put('5', Collections.emptyList());
        takeOneMatchFromNumberOrSymbol.put('6', List.of('5'));
        takeOneMatchFromNumberOrSymbol.put('7', List.of('1'));
        takeOneMatchFromNumberOrSymbol.put('8', List.of('0', '6', '9'));
        takeOneMatchFromNumberOrSymbol.put('9', List.of('5'));
        takeOneMatchFromNumberOrSymbol.put('+', List.of('-'));
        takeOneMatchFromNumberOrSymbol.put('-', Collections.emptyList());
        takeOneMatchFromNumberOrSymbol.put('=', Collections.emptyList());
    }

    {
        giveOneMatchFromNumberOrSymbol.put('0', List.of('8'));
        giveOneMatchFromNumberOrSymbol.put('1', List.of('7'));
        giveOneMatchFromNumberOrSymbol.put('2', Collections.emptyList());
        giveOneMatchFromNumberOrSymbol.put('3', List.of('9'));
        giveOneMatchFromNumberOrSymbol.put('4', Collections.emptyList());
        giveOneMatchFromNumberOrSymbol.put('5', List.of('6', '9'));
        giveOneMatchFromNumberOrSymbol.put('6', List.of('8'));
        giveOneMatchFromNumberOrSymbol.put('7', Collections.emptyList());
        giveOneMatchFromNumberOrSymbol.put('8', Collections.emptyList());
        giveOneMatchFromNumberOrSymbol.put('9', List.of('8'));
        giveOneMatchFromNumberOrSymbol.put('+', Collections.emptyList());
        giveOneMatchFromNumberOrSymbol.put('-', List.of('+'));
        giveOneMatchFromNumberOrSymbol.put('=', Collections.emptyList());
    }

    Map<String, Set<String>> insideSingleNumber(String solution) {
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

    Map<String, Set<String>> insideEquation(String solution) {
        Map<String, Set<String>> quizzesAndSolutionsWithinEquation = new HashMap<>();

        for (int i = 0; i < solution.length(); i++) {
            char numberOrSymbolToBeReplaced = solution.charAt(i);
            var numbersAndSymbolsWithTakenMatch = takeOneMatchFromNumberOrSymbol.get(numberOrSymbolToBeReplaced);

            for (char numberWithTakenMatch : numbersAndSymbolsWithTakenMatch) {
                String quizWithTakenMatch = characterChanger.changeCharactersInString(solution, i, numberWithTakenMatch);
                for (int k = 0; k < quizWithTakenMatch.length(); k++) {
                    if (k != i) {
                        char numberOrSymbolToAddMatchTo = quizWithTakenMatch.charAt(k);
                        var numbersAndSymbolsWithAddedMatch = giveOneMatchFromNumberOrSymbol.get(numberOrSymbolToAddMatchTo);
                        for (char characterOrSymbolWithAddedMatch : numbersAndSymbolsWithAddedMatch) {
                            String quizWithGivenMatch = characterChanger.changeCharactersInString(quizWithTakenMatch, k, characterOrSymbolWithAddedMatch);
                            if (!equationMathChecker.isMathematicallyCorrect(quizWithGivenMatch)) {
                                quizzesAndSolutionsWithinEquation.putIfAbsent(quizWithGivenMatch, new HashSet<>());
                                quizzesAndSolutionsWithinEquation.get(quizWithGivenMatch).add(solution);
                            }
                        }
                    }
                }
            }
        }
        return quizzesAndSolutionsWithinEquation;
    }
}
