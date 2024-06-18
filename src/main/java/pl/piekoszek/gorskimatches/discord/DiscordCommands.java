package pl.piekoszek.gorskimatches.discord;

import pl.piekoszek.gorskimatches.messenger.QuizHandler;
import pl.piekoszek.gorskimatches.validation.StringEditor;

import java.util.List;

class DiscordCommands {

    private final QuizHandler quizHandler;

    public DiscordCommands(QuizHandler quizHandler) {
        this.quizHandler = quizHandler;
    }

    public List<String> handleCommands(String message, String id) {
        String processedMessage = StringEditor.removeSpaces(message.toLowerCase());

        if (processedMessage.matches("hello") || processedMessage.matches("hi")) {
            return helloMessage();
        }

        if (processedMessage.matches("challenge")) {
            return quiz(id);
        }

        if (processedMessage.matches("\\b\\s*\\d\\s*[+-]\\s*\\d\\s*=\\s*\\d\\s*\\b\\s*")) {
            return check(id, processedMessage);
        }

        if (processedMessage.matches("info")) {
            return info();
        }

        if (processedMessage.matches("contact")) {
            return contact();
        }

        return commands();
    }

    private List<String> commands() {
        return List.of("List of supported commands: hello, challenge, info, contact");
    }

    private List<String> helloMessage() {
        return List.of("Hi there! I am a simple bot that responds to commands. You can solve quizzes as in the matchbook that say: Move one match to make equation correct.\n" +
                "To start simply type in: challenge. You will receive a quiz to solve, then type in your answer in format : 0+0=0\nGood luck!");
    }

    private List<String> quiz(String id) {
        return List.of((quizHandler.createUrl(quizHandler.generateQuiz(id))));
    }

    private List<String> check(String id, String answer) {
        if (quizHandler.isStarted(id)) {
            if (quizHandler.checkQuiz(id, answer)) {
                quizHandler.cleanUpAfterQuiz(id);
                return List.of("Great Job! For another quiz, type in: challenge");
            } else {
                return List.of("Hmm, try again!");
            }
        } else {
            return List.of("To start type in: challenge");
        }
    }

    private List<String> info() {
        return List.of("Here you can solve as in quizzes on the matchbook that say: Move one match to make equation correct.\n" +
                "To solve a quiz type in your answer in a format: 0+0=0. Good luck!\n" +
                "For list of handled commands type in: commands");
    }

    private List<String> contact() {
        return List.of("To contact me please write to: gorskimatchesserver@gmail.com");
    }
}