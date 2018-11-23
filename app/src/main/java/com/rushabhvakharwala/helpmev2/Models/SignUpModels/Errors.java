package com.rushabhvakharwala.helpmev2.Models.SignUpModels;

public class Errors {
    final String email;
    final String password;

    public Errors(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
