package pl.piekoszek.gorskimatches.facebook;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("web")
public class FacebookTestController {

    private FacebookMessageService facebookMessageService;

    private FacebookEntry facebookEntry;

    FacebookTestController(FacebookMessageService facebookMessageService, FacebookEntry facebookEntry){
        this.facebookMessageService = facebookMessageService;
        this.facebookEntry = facebookEntry;
    }

    @GetMapping
    ResponseEntity<String> verifyToken(@RequestParam(value = "hub.mode", defaultValue = "") String mode,
                                       @RequestParam(value = "hub.verify_token", defaultValue = "") String verifyToken,
                                       @RequestParam(value = "hub.challenge", defaultValue = "") String challenge) {
        return ResponseEntity.ok(challenge);
    }

    @PostMapping
    public void post(@RequestBody FacebookHookRequest request) {
        request.getEntry().forEach(entry -> entry.getMessaging().forEach(message -> {
            facebookEntry.setId(message.getSender().get("id"));
        }));
        facebookMessageService.sendReply(facebookEntry.getId(), "hello");
    }
}
