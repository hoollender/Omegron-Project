package com.omegron.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class UserLoginDTO {
    @NotBlank(message = "Please enter an email address!")
    @Email
    private String email;
    @NotBlank(message = "Please enter a password!")
    @Size(min = 3, max = 20, message = "Password is invalid!")
    private String password;

    public UserLoginDTO() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail (String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}