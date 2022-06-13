package pl.piekoszek.gorskimatches.token;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
class AccountController {
    private EmailService emailServiceImplemented;

    AccountController(EmailService emailServiceImplemented){
        this.emailServiceImplemented = emailServiceImplemented;
    }

    @PostMapping("email")
    void getEmail(@RequestBody AccountInfo accountInfo) {
        emailServiceImplemented.sendRegistrationLink(accountInfo.getEmail());
    }
}
