package pl.piekoszek.gorskimatches.token;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "accountInfo")
public class AccountInfo {

    @Id
    @Column(name = "email")
    private String email;
    @Column(name = "accountname")
    private String accountName;

    public AccountInfo() {
    }
    public AccountInfo(String email, String accountName) {
        this.email = email;
        this.accountName = accountName;
    }
    public String getEmail() {
        return email;
    }
    public String getAccountName() {
        return accountName;
    }
    public void setAccountName(String accountName){
        this.accountName = accountName;
    }
    @Override
    public String toString(){
        return "Account Info [" + " Email: " + email +", Account Name: " + accountName;
    }
}
