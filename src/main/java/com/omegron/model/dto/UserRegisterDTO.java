package com.omegron.model.dto;

import jakarta.validation.constraints.*;

public class UserRegisterDTO {
    @NotBlank(message = "{firstName.required}")
    @Size(min = 3, max = 50, message = "{firstName.size}")
    private String firstName;

    @NotBlank(message = "{lastName.required}")
    @Size(min = 3, max = 50, message = "{lastName.size}")
    private String lastName;

    @NotBlank(message = "{email.required}")
    @Email(message = "{email.invalid}")
    private String email;

    @NotBlank(message = "{password.required}")
    @Size(min = 3, max = 20, message = "{password.size}")
    private String password;

    @NotBlank(message = "{confirmPassword.required}")
    @Size(min = 3, max = 20, message = "{confirmPassword.size}")
    private String confirmPassword;


    public String getFirstName() {
        return firstName;
    }

    public UserRegisterDTO setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public UserRegisterDTO setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserRegisterDTO setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserRegisterDTO setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    @Override
    public String toString() {
        return "UserRegistrationDTO{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", password='" + (password == null ? "N/A" : "[PROVIDED]") + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}