package pl.piekoszek.gorskimatches.token;


import org.springframework.web.bind.annotation.*;
import pl.piekoszek.gorskimatches.repository.AccountRepository;
import pl.piekoszek.gorskimatches.validation.StringEditor;

import javax.validation.Valid;

@RestController
@RequestMapping("api/auth")
class AccountController {
    private AccountRepository accountRepository;
    private EmailService emailService;
    private StringEditor patternMatches;

    AccountController(EmailService emailService, AccountRepository accountRepository, StringEditor patternMatches) {
        this.emailService = emailService;
        this.accountRepository = accountRepository;
        this.patternMatches = patternMatches;
    }

    @PostMapping("email")
    String sendRegistrationEmail(@Valid @RequestBody RegistrationRequest sendRegistrationRequest) {
            emailService.sendRegistrationOrLoginLink(sendRegistrationRequest.getEmail());
            return "Email has been sent";
    }

    @PostMapping("account")
    void changeAccountInfo(@Email String email, @Valid @RequestBody AccountInfo accountInfo) {
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
