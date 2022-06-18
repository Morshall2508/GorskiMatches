package pl.piekoszek.gorskimatches.token;

import javax.persistence.*;

@Entity
public class AccountInfo {

    @Id
    private String email;
    private String accountName;
    @Column(columnDefinition = "TEXT")
    private String avatar;

    public AccountInfo() {
    }

    public AccountInfo(String email, String accountName, String avatar) {
        this.email = email;
        this.accountName = accountName;
        this.avatar = avatar;
    }

    public String getEmail() {
        return email;
    }

    public String getAccountName() {
        return accountName;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }
}
