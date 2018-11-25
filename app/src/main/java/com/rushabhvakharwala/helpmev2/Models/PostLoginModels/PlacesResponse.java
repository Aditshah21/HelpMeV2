package com.rushabhvakharwala.helpmev2.Models.PostLoginModels;



import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class PlacesResponse {

    @SerializedName("nearby_places")
    @Expose
    private ArrayList<NearbyPlaces> nearby_places = new ArrayList<>();

    public PlacesResponse(ArrayList<NearbyPlaces> nearby_places) {
        this.nearby_places = nearby_places;
    }

    public void setNearby_places(ArrayList<NearbyPlaces> nearby_places) {
        this.nearby_places = nearby_places;
    }

    public ArrayList<NearbyPlaces> getNearby_places() {
        return nearby_places;
    }
}
