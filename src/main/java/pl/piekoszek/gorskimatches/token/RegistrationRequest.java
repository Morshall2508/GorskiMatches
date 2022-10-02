package pl.piekoszek.gorskimatches.token;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class RegistrationRequest {

    @Email(message = "Email has to have proper format")
    @NotBlank(message = "Email cannot be blank")
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
