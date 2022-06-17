package pl.piekoszek.gorskimatches.token;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.piekoszek.gorskimatches.repository.AccountRepository;
import pl.piekoszek.gorskimatches.token.AccountInfo;

import java.util.Collections;

@CrossOrigin
@RestController
@RequestMapping("auth")
class AccountController {
    @Autowired
    AccountRepository accountRepository;

    private EmailService emailService;

    AccountController(EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping("email")
    void getEmail(@RequestBody AccountInfo accountInfo) {
        emailService.sendRegistrationLink(accountInfo.getEmail());
    }

    @PostMapping("account")
    void changeAccountInfo(@RequestBody AccountInfo accountInfo) {
        accountRepository.save(new AccountInfo(accountInfo.getEmail(), accountInfo.getAccountName(), accountInfo.getAvatar()));
    }
}
