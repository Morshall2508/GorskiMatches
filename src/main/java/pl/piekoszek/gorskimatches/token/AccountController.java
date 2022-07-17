package pl.piekoszek.gorskimatches.token;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.piekoszek.gorskimatches.repository.AccountRepository;

import java.util.Optional;

@RestController
@RequestMapping("api/auth")
class AccountController {

    private AccountRepository accountRepository;
    private EmailService emailService;

    private TokenDecoder tokenDecoder;

    AccountController(EmailService emailService, AccountRepository accountRepository, TokenDecoder tokenDecoder) {
        this.emailService = emailService;
        this.accountRepository = accountRepository;
        this.tokenDecoder = tokenDecoder;
    }

    @PostMapping("email")
    void sendRegistrationEmail(@RequestBody AccountInfo accountInfo) {
        emailService.sendRegistrationOrLoginLink(accountInfo.getEmail());
    }

    @PostMapping("account")
    void changeAccountInfo(@Email String email, @RequestBody AccountInfo accountInfo) {
        try {
            if (email.equals())
                accountRepository.save(accountInfo);
        } catch ()
    }

    @GetMapping("accountInfo/{email}")
    AccountInfo fetchAccountInfo(@PathVariable("email") AccountInfo accountInfo) {
        return accountRepository.findById(accountInfo.getEmail()).get();
    }
}
