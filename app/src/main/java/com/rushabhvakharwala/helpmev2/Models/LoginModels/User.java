package com.rushabhvakharwala.helpmev2.Models.LoginModels;

public class User {

    String error;
    String error_description;
    String access_token;
    String token_type;
    int expires_in;
    String refresh_token;
    String created_at;

    public User(String access_token, String token_type, int expires_in, String refresh_token, String created_at) {
        this.access_token = access_token;
        this.token_type = token_type;
        this.expires_in = expires_in;
        this.refresh_token = refresh_token;
        this.created_at = created_at;
    }

    public User(String error, String error_description) {
        this.error = error;
        this.error_description = error_description;
    }



    public String getError() {
        return error;
    }

    public String getError_description() {
        return error_description;
    }

    public String getAccess_token() {
        return access_token;
    }

    public String getToken_type() {
        return token_type;
    }

    public int getExpires_in() {
        return expires_in;
    }

    public String getRefresh_token() {
        return refresh_token;
    }

    public String getCreated_at() {
        return created_at;
    }
}
