package pl.piekoszek.gorskimatches.token;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
class AccountController {
    private TokenCreator tokenCreator;
    private AccountInfo accountInfo;
    private EmailServiceImplemented emailServiceImplemented;

    AccountController(TokenCreator tokenCreator, AccountInfo accountInfo, EmailServiceImplemented emailServiceImplemented) {
        this.tokenCreator = tokenCreator;
        this.accountInfo = accountInfo;
        this.emailServiceImplemented = emailServiceImplemented;
    }

    @GetMapping("token")
    String getToken(@RequestParam("token") String token) {
        return TokenCreator.jwtToken(accountInfo.getEmail());
    }

    @PostMapping("email")
    String getEmail(@RequestBody AccountInfo accountInfo) {
        emailServiceImplemented.sendRegistrationLink(accountInfo.getEmail());
        return null;
    }
}
