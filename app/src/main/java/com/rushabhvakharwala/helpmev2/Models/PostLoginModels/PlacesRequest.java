package com.rushabhvakharwala.helpmev2.Models.PostLoginModels;

import com.rushabhvakharwala.helpmev2.Models.PostLoginModels.Location._NearbyPlace;

public class PlacesRequest {
    private _NearbyPlace nearby_place;

    public PlacesRequest(_NearbyPlace nearby_place) {
        this.nearby_place = nearby_place;
    }

    public _NearbyPlace getNearby_place() {
        return nearby_place;
    }
}
