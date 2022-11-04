package pl.piekoszek.gorskimatches.facebook;

import org.springframework.stereotype.Component;
import pl.piekoszek.gorskimatches.repository.FacebookRepository;
import pl.piekoszek.gorskimatches.validation.StringEditor;

@Component
public class FacebookCommands {

    private final FacebookMessageService messageService;

    private final StringEditor stringEditor;

    private final FacebookRepository facebookRepository;

    FacebookCommands(FacebookMessageService messageService, StringEditor stringEditor, FacebookRepository facebookRepository) {
        this.messageService = messageService;
        this.stringEditor = stringEditor;
        this.facebookRepository = facebookRepository;
    }

    void commands(FacebookMessageReceived messageReceived, String id) {
        if (messageReceived.getText().toLowerCase().matches(stringEditor.removeSpaces("commands"))) {
            messageService.sendReply(id, "List of supported commands: hello, challenge, info, contact");
        }
    }

    void helloMessage(FacebookMessageReceived messageReceived, String id) {
        if (messageReceived.getText().toLowerCase().matches(stringEditor.removeSpaces("hello")) || messageReceived.getText().toLowerCase().matches(stringEditor.removeSpaces("hi"))) {
            messageService.sendReply(id, "Welcome to my facebook site! Here you can solve as in quizzes on the matchbook that say: Move one match to make equation correct.");
            messageService.sendReply(id, "To start simply type in: challenge. You will receive a quiz to solve, then type in your answer in format : 0+0=0\nGood luck!");
        }
    }

    void quiz(FacebookMessageReceived messageReceived, String id) {
        if (messageReceived.getText().toLowerCase().matches(stringEditor.removeSpaces("challenge"))) {
            messageService.sendAttachmentPhoto(id);
        }
    }

    void check(FacebookMessageReceived messageReceived, String id) {
        if (messageReceived.getText().matches((stringEditor.removeSpaces("\\b\\s*\\d\\s*[+-]\\s*\\d\\s*=\\s*\\d\\s*\\b\\s*")))) {
            if (notStarted(id)) {
                messageService.sendReply(id, "To start type in: challenge");
            } else {
                messageService.sendResult(id, messageReceived.getText());
            }
        }
    }

    void info(FacebookMessageReceived messageReceived, String id) {
        if (messageReceived.getText().toLowerCase().matches(stringEditor.removeSpaces("info"))) {
            messageService.sendReply(id, "Here you can solve as in quizzes on the matchbook that say: Move one match to make equation correct.");
            messageService.sendReply(id, "To solve a quiz type in your answer in a format: 0+0=0\\nGood luck!");
            messageService.sendReply(id, "For list of handled commands type in: commands");
        }
    }

    void contact(FacebookMessageReceived messageReceived, String id) {
        if (messageReceived.getText().toLowerCase().matches(stringEditor.removeSpaces("contact"))) {
            messageService.sendReply(id, "To contact me please write to: gorskimatchesserver@gmail.com");
        }
    }

    boolean notStarted(String id) {
        return !facebookRepository.existsById(id);
    }
}
