package com.omegron.model.dto;

import jakarta.validation.constraints.*;

public class UserRegisterDTO {
    @NotBlank(message = "Username is required!")
    @Size(min = 3, max = 50, message = "First name length must be between 3 and 50 characters!")
    private String firstName;
    @NotBlank(message = "Username is required!")
    @Size(min = 3, max = 50, message = "Last name length must be between 3 and 50 characters!")
    private String lastName;
    @NotBlank(message = "Email is required!")
    @Email(message = "Please provide a valid email address!")
    private String email;
    @NotBlank(message = "Password is required!")
    @Size(min = 3, max = 20, message = "Password length must be between 3 and 20 characters!")
    private String password;
    @NotBlank(message = "Confirm password is required!")
    @Size(min = 3, max = 20, message = "Confirm password length must be between 3 and 20 characters!")
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