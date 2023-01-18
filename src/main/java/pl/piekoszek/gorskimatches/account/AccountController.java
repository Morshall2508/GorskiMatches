package pl.piekoszek.gorskimatches.account;

import org.springframework.web.bind.annotation.*;
import pl.piekoszek.gorskimatches.config.authorization.Email;
import pl.piekoszek.gorskimatches.config.authorization.ForbiddenException;

import javax.validation.Valid;

@RestController
@RequestMapping("api/auth")
class AccountController {

    private final AccountService accountService;

    AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("email")
    String sendRegistrationEmail(@Valid @RequestBody RegistrationRequest sendRegistrationRequest) {
        accountService.sendRegistrationOrLoginLink(sendRegistrationRequest.getEmail());
        return "Email has been sent";
    }

    @PostMapping("account")
    void changeAccountInfo(@Email String email, @Valid @RequestBody AccountInfo accountInfo) {
        if (!email.equals(accountInfo.getEmail())) {
            throw new ForbiddenException("Incorrect email");
        }
        accountService.changeAccountInfo(accountInfo);
    }

    @GetMapping("accountInfo/{email}")
    AccountInfo fetchAccountInfo(@PathVariable("email") String email) {
        return accountService.getAccountInfo(email);
    }

    @GetMapping("accountList")
    Iterable<AccountInfo> getAll() {
        return accountService.getAllAccountsInformation();
    }
}
