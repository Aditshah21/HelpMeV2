package com.rushabhvakharwala.helpmev2.Models.PostLoginModels;

import java.util.List;

public class PlacesResponse {
    List<NearbyPlaces> nearby_places;

    public PlacesResponse(List<NearbyPlaces> nearby_places) {
        this.nearby_places = nearby_places;
    }

    public List<NearbyPlaces> getNearby_places() {
        return nearby_places;
    }
}
