package pl.piekoszek.gorskimatches.token;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
class AccountController {
    private EmailService emailService;

    AccountController(EmailService emailService){
        this.emailService = emailService;
    }

    @PostMapping("email")
    void getEmail(@RequestBody AccountInfo accountInfo) {
        emailService.sendRegistrationLink(accountInfo.getEmail());
    }
}
