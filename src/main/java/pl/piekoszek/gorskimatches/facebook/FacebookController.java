package pl.piekoszek.gorskimatches.facebook;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
    @RequestMapping("api/webhook/facebook/page/message")
public class FacebookController {

    private FacebookMessageService facebookMessageService;

    @Value("${VERIFY_TOKEN}")
    private String VERIFY_TOKEN;

    FacebookController(FacebookMessageService facebookMessageService) {
        this.facebookMessageService = facebookMessageService;
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
        request.getEntry().forEach(entry -> entry.getMessaging().forEach(message -> {
            facebookEntry.setId(message.getSender().get("id"));
        }));
//        facebookMessageService.sendHelloReply(facebookEntry.getId());
//        facebookMessageService.sendAtt(facebookEntry.getId());
        facebookMessageService.sendAttachmentPhoto(facebookEntry.getId());
    }
}
