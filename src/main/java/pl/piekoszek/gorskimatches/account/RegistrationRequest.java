package pl.piekoszek.gorskimatches.account;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

class RegistrationRequest {

    @Email(message = "Email has to have proper format")
    @NotBlank(message = "Email cannot be blank")
    private String email;

    public String getEmail() {
        return email;
    }
}
