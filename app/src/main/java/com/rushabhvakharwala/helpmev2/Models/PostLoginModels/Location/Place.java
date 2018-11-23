package com.rushabhvakharwala.helpmev2.Models.PostLoginModels.Location;

public class Place {
    private String place_id;
    private UserLocation user_locaion_attributes;

    public Place(UserLocation user_locaion_attributes) {
        this.user_locaion_attributes = user_locaion_attributes;
    }

    public UserLocation getUser_locaion_attributes() {
        return user_locaion_attributes;
    }
}
