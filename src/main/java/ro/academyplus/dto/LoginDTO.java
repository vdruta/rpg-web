package ro.academyplus.dto;

import ro.academyplus.dto.validators.Email;

import javax.validation.constraints.NotNull;

/**
 * Created by MM on 2016-03-07.
 */

public class LoginDTO {
    @Email(message = "Invalid password. You have 5 more tries!")
    @NotNull
    private String email;
    @NotNull
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
