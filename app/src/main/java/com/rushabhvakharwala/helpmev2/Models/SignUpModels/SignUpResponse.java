package com.rushabhvakharwala.helpmev2.Models.SignUpModels;

public class SignUpResponse {
    final Errors errors;
    final String id;
    final String email;
    final String created_at;
    final String updated_at;

    public SignUpResponse(Errors errors, String id, String email, String created_at, String updated_at) {
        this.errors = errors;
        this.id = id;
        this.email = email;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public Errors getErrors() {
        return errors;
    }

    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getCreated_at() {
        return created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }
}
