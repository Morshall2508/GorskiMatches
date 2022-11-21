package pl.piekoszek.gorskimatches.account;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
public class AccountInfo {

    @Id
    private String email;

    @NotBlank(message = "Account name is mandatory")
    private String accountName;

    @Column(columnDefinition = "TEXT")
    private String avatar;

    public String getEmail() {
        return email;
    }

    public String getAccountName() {
        return accountName;
    }

    public String getAvatar() {
        return avatar;
    }
}
