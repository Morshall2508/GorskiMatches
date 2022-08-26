package pl.piekoszek.gorskimatches.token;

import org.springframework.web.bind.annotation.*;
import pl.piekoszek.gorskimatches.repository.AccountRepository;
import pl.piekoszek.gorskimatches.validation.PatternMatches;

@RestController
@RequestMapping("api/auth")
class AccountController {
    private AccountRepository accountRepository;
    private EmailService emailService;
    private PatternMatches patternMatches;

    AccountController(EmailService emailService, AccountRepository accountRepository, PatternMatches patternMatches) {
        this.emailService = emailService;
        this.accountRepository = accountRepository;
        this.patternMatches = patternMatches;
    }

    @PostMapping("email")
    String sendRegistrationEmail(@RequestBody AccountInfo accountInfo) {
        if (patternMatches.checkIfPatternIsCorrect(accountInfo.getEmail(), "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
                + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$")) {
            emailService.sendRegistrationOrLoginLink(accountInfo.getEmail());
            return "Email has been sent";
        }
        return "Incorrect email";
    }

    @PostMapping("account")
    void changeAccountInfo(@Email String email, @RequestBody AccountInfo accountInfo) {
        if (!email.equals(accountInfo.getEmail())) {
            throw new ForbiddenException("Incorrect email");
        }
        accountRepository.save(accountInfo);
    }

    @GetMapping("accountInfo/{email}")
    AccountInfo fetchAccountInfo(@PathVariable("email") AccountInfo accountInfo) {
        return accountRepository.findById(accountInfo.getEmail()).get();
    }
}
