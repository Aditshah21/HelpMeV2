package com.rushabhvakharwala.helpmev2.Models.LoginModels;

import java.io.Serializable;

public class UserToken implements Serializable {

        final User user;

        public UserToken(User user) {
            this.user = user;
        }

        public User getUser() {
            return user;
        }
    }