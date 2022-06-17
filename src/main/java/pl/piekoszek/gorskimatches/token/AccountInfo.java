package pl.piekoszek.gorskimatches.token;

import javax.persistence.*;

@Entity
@Table(name = "accountInfo")
public class AccountInfo {

    @Id
    @Column(name = "email")
    private String email;
    @Column(name = "accountName")
    private String accountName;
    @Column(name = "avatar")
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
