package pl.piekoszek.gorskimatches.token;

import org.springframework.web.bind.annotation.*;
import pl.piekoszek.gorskimatches.repository.AccountRepository;

@RestController
@RequestMapping("api/auth")
class AccountController {
    private AccountRepository accountRepository;
    private EmailService emailService;

    AccountController(EmailService emailService, AccountRepository accountRepository) {
        this.emailService = emailService;
        this.accountRepository = accountRepository;
    }

    @PostMapping("email")
    void sendRegistrationEmail(@RequestBody AccountInfo accountInfo) {
        emailService.sendRegistrationOrLoginLink(accountInfo.getEmail());
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
