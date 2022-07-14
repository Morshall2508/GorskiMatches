package pl.piekoszek.gorskimatches.token;

public class TokenData {
    private String email;

    public TokenData(Email email){
        this.email = String.valueOf(email);
    }

    public TokenData() {

    }
}
