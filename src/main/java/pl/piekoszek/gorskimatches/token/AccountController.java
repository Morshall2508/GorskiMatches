package pl.piekoszek.gorskimatches.token;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
class AccountController {
    private EmailServiceImplemented emailServiceImplemented;

    @PostMapping("email")
    void getEmail(@RequestBody AccountInfo accountInfo) {
        emailServiceImplemented.sendRegistrationLink(accountInfo.getEmail());
    }
}
