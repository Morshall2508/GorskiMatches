package pl.piekoszek.gorskimatches.facebook;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/webhook/facebook/page/message")
public class FacebookController {

    private FacebookMessageService facebookMessageService;

    @Value("${VERIFY_TOKEN}")
    private String VERIFY_TOKEN;

    FacebookController(FacebookMessageService facebookMessageService) {
        this.facebookMessageService = facebookMessageService;
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public String get(@RequestParam(name = "hub.verify_token") String token,
                      @RequestParam(name = "hub.challenge") String challenge) {
        if (token != null && !token.isEmpty() && token.equals(VERIFY_TOKEN)) {
            return challenge;
        } else {
            return "Wrong Token";
        }
    }

    @PostMapping
    public void post(@RequestBody FacebookHookRequest request) {
        FacebookEntry facebookEntry = new FacebookEntry();
        request.getEntry().forEach(entry -> entry.getMessaging().forEach(message -> {
            facebookEntry.setId(message.getSender().get("id"));
        }));
        facebookMessageService.sendHelloReply(facebookEntry.getId());
    }
}
