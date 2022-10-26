package pl.piekoszek.gorskimatches.facebook;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.piekoszek.gorskimatches.validation.StringEditor;

import java.util.concurrent.atomic.AtomicReference;

@RestController
@RequestMapping("api/webhook/facebook/page/message")
public class FacebookController {

    private FacebookMessageService facebookMessageService;

    private StringEditor stringEditor;

    @Value("${VERIFY_TOKEN}")
    private String VERIFY_TOKEN;

    FacebookController(FacebookMessageService facebookMessageService, StringEditor stringEditor) {
        this.facebookMessageService = facebookMessageService;
        this.stringEditor = stringEditor;
    }

    @GetMapping
    public ResponseEntity<String> get(@RequestParam(name = "hub.verify_token") String token,
                                      @RequestParam(name = "hub.challenge") String challenge) {
        if (token != null && !token.isEmpty() && token.equals(VERIFY_TOKEN)) {
            return ResponseEntity.ok(challenge);
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Wrong Token");
        }
    }

    @PostMapping
    public void post(@RequestBody FacebookHookRequest request) {
        FacebookEntry facebookEntry = new FacebookEntry();
        request.getEntry().forEach(entry -> entry.getMessaging().forEach(message
                -> {
            facebookEntry.setId(message.getSender().get("id"));
            if (message.getMessage().getText().toLowerCase().matches(stringEditor.removeSpaces("challenge"))) {
                facebookMessageService.sendAttachmentPhoto(facebookEntry.getId());
            } else if (message.getMessage().getText().toLowerCase().matches("\\b\\s*\\d\\s*[+-]\\s*\\d\\s*=\\s*\\d\\s*\\b\\s*")) {
                facebookMessageService.sendResult(facebookEntry.getId(), stringEditor.removeSpaces(message.getMessage().getText()));
            } else {
                facebookMessageService.sendHelloReply(facebookEntry.getId());
            }
        }));
    }
}


