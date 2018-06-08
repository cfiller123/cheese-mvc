package org.launchcode.cheesemvc.models;

import javax.validation.constraints.Email;
import javax.validation.constraints.NegativeOrZero;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class User {

    @NotNull
    @Size(min=5, max=15)
    private String username;

    @NotNull
    @Email
    private String email;

    @NotNull
    private String password;

    @NotNull(message="Passwords do not match")
    private String verifyPassword;

    public User() {

    }

    private void checkPassword(String password, String verifyPassword) {
        if ((!password.equals(verifyPassword)) || password.isEmpty()) {
            this.verifyPassword = null;
        }

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
        checkPassword(password, verifyPassword);
    }

    public String getVerifyPassword() {
        return verifyPassword;
    }

    public void setVerifyPassword(String verifyPassword) {
        this.verifyPassword = verifyPassword;
        checkPassword(password, verifyPassword);
    }
}