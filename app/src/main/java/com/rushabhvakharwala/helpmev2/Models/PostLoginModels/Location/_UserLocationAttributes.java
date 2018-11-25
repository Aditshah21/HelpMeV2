package com.rushabhvakharwala.helpmev2.Models.PostLoginModels.Location;

public class _UserLocationAttributes {

    double lat;
    double lng;

    public _UserLocationAttributes(double lat, double lng) {
        this.lat = lat;
        this.lng = lng;
    }

    public double getLat() {
        return lat;
    }

    public double getLng() {
        return lng;
    }
}
