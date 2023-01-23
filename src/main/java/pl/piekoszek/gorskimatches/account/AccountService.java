package pl.piekoszek.gorskimatches.account;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import pl.piekoszek.gorskimatches.config.http.NotFoundException;
import pl.piekoszek.gorskimatches.email.EmailService;
import pl.piekoszek.gorskimatches.token.TokenService;

import javax.mail.MessagingException;

@Component
class AccountService {

    private final AccountRepository accountRepository;
    private final EmailService emailService;
    private final TokenService tokenService;
    private final String server;

    AccountService(AccountRepository accountRepository, EmailService emailService,
                   TokenService tokenService, @Value("${matches.server.address}") String server) {
        this.accountRepository = accountRepository;
        this.emailService = emailService;
        this.tokenService = tokenService;
        this.server = server;
    }

    void sendRegistrationOrLoginLink(String email) throws MessagingException {
        emailService.sendEmail(email, "Account registration",
                        "To register click on this link: " + "<a href=" + server + "auth/login.html?token=" + tokenService.encode(email) + ">" + "Register" + "</a>");
    }

    void changeAccountInfo(AccountInfo accountInfo) {
        accountRepository.save(accountInfo);
    }

    AccountInfo getAccountInfo(String email) {
        return accountRepository.findById(email).orElseThrow(()
                -> new NotFoundException("Cannot find user with email: " + email));
    }
}
