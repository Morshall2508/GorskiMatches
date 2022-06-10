package pl.piekoszek.gorskimatches.token;

import org.springframework.web.bind.annotation.*;
import pl.piekoszek.gorskimatches.equation.Equation;

import java.util.function.ToLongBiFunction;

@RestController
@RequestMapping("auth")
class AccountController {
    private TokenCreator tokenCreator;
    private AccountInfo accountInfo;

    AccountController(TokenCreator tokenCreator, AccountInfo accountInfo) {
        this.tokenCreator = tokenCreator;
        this.accountInfo = accountInfo;
    }

    @PostMapping("email")
    String getToken(@RequestBody AccountInfo accountInfo) {
        return TokenCreator.jwtToken(accountInfo.getEmail());
    }
}
