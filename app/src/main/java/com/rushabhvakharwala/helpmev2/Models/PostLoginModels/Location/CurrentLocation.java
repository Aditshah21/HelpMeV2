package com.rushabhvakharwala.helpmev2.Models.PostLoginModels.Location;

public class CurrentLocation {
    private Place nearby_place;

    public CurrentLocation(Place nearby_place) {
        this.nearby_place = nearby_place;
    }

    public Place getNearby_place() {
        return nearby_place;
    }
}
