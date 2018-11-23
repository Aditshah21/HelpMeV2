package com.rushabhvakharwala.helpmev2.Models.PostLoginModels;

import com.rushabhvakharwala.helpmev2.Models.PostLoginModels.Location.CurrentLocation;

public class PlacesRequest {
    private CurrentLocation nearby_place;

    public PlacesRequest(CurrentLocation nearby_place) {
        this.nearby_place = nearby_place;
    }

    public CurrentLocation getNearby_place() {
        return nearby_place;
    }
}
