package pl.piekoszek.gorskimatches.token;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.piekoszek.gorskimatches.repository.AccountRepository;

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

    public ResponseEntity<AccountInfo> passAccount(@RequestBody AccountInfo accountInfo) {
        try {
            AccountInfo _accountInfo = accountRepository
                    .save(new AccountInfo(accountInfo.getEmail(), accountInfo.getAccountName()));
            return new ResponseEntity<>(_accountInfo, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
