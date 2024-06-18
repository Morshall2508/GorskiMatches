package pl.piekoszek.gorskimatches.facebook;

import org.springframework.stereotype.Component;
import pl.piekoszek.gorskimatches.messenger.QuizHandler;
import pl.piekoszek.gorskimatches.messenger.QuizUrlCreator;
import pl.piekoszek.gorskimatches.validation.StringEditor;

import java.util.List;

@Component
public class FacebookCommands {

    private final QuizHandler quizHandler;
    private final QuizUrlCreator quizUrlCreator;

    FacebookCommands(QuizHandler quizHandler, QuizUrlCreator quizUrlCreator) {
        this.quizHandler = quizHandler;
        this.quizUrlCreator = quizUrlCreator;
    }

    public List<FacebookCommandResponse> handleCommands(FacebookMessageReceived messageReceived, String id) {

        String message = StringEditor.removeSpaces(messageReceived.getText().toLowerCase());

        if (message.matches("hello") ||
                message.matches("hi")) {
            return helloMessage();
        }

        if (message.matches("challenge")) {
            return quiz(id);
        }

        if (message.matches("\\b\\s*\\d\\s*[+-]\\s*\\d\\s*=\\s*\\d\\s*\\b\\s*")) {
            return check(id, message);
        }

        if (message.matches("info")) {
            return info();
        }

        if (message.matches("contact")) {
            return contact();
        }
        return commands();
    }

    private List<FacebookCommandResponse> commands() {
        return List.of(FacebookCommandResponse.ofMessage("List of supported commands: hello, challenge, info, contact"));
    }

    private List<FacebookCommandResponse> helloMessage() {
        return List.of(FacebookCommandResponse.ofMessage("Welcome to my facebook site! Here you can solve as in quizzes on the matchbook that say: Move one match to make equation correct."),
                FacebookCommandResponse.ofMessage("To start simply type in: challenge. You will receive a quiz to solve, then type in your answer in format : 0+0=0\nGood luck!"));
    }

    private List<FacebookCommandResponse> quiz(String id) {
        return List.of(FacebookCommandResponse.ofAttachment(quizUrlCreator.createUrl(quizHandler.generateQuiz(id))));
    }

    private List<FacebookCommandResponse> check(String id, String answer) {
        if (isStarted(id)) {
            if (quizHandler.checkQuiz(id, answer)) {
                quizHandler.cleanUpAfterQuiz(id);
                return List.of(FacebookCommandResponse.ofMessage("Great Job! For another quiz, type in: challenge"));
            } else {
                return List.of(FacebookCommandResponse.ofMessage("Hmm, try again!"));
            }
        } else {
            return List.of(FacebookCommandResponse.ofMessage("To start type in: challenge"));
        }
    }

    private List<FacebookCommandResponse> info() {
        return List.of(FacebookCommandResponse.ofMessage("Here you can solve as in quizzes on the matchbook that say: Move one match to make equation correct."),
                FacebookCommandResponse.ofMessage("To solve a quiz type in your answer in a format: 0+0=0. Good luck!"),
                FacebookCommandResponse.ofMessage("For list of handled commands type in: commands"));
    }

    private List<FacebookCommandResponse> contact() {
        return List.of(FacebookCommandResponse.ofMessage("To contact me please write to: gorskimatchesserver@gmail.com"));
    }

    private boolean isStarted(String id) {
        return quizHandler.isStarted(id);
    }
}
