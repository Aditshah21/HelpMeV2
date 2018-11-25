package com.rushabhvakharwala.helpmev2.Models.PostLoginModels.Location;

public class _NearbyPlace {
    private _UserLocationAttributes nearby_userLocationAttributes;

    public _NearbyPlace(_UserLocationAttributes nearby_userLocationAttributes) {
        this.nearby_userLocationAttributes = nearby_userLocationAttributes;
    }

    public _UserLocationAttributes getNearby_userLocationAttributes() {
        return nearby_userLocationAttributes;
    }
}
