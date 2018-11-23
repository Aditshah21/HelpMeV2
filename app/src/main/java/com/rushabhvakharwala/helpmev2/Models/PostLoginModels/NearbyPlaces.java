package com.rushabhvakharwala.helpmev2.Models.PostLoginModels;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.InputStream;

public class NearbyPlaces {
    private String name;
    private String vicinity;
    private String icon;
    private float rating;
    private String status;

    public NearbyPlaces(String name, String vicinity, String icon, float rating, String status) {
        this.name = name;
        this.vicinity = vicinity;
        this.icon = icon;
        this.rating = rating;
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public String getVicinity() {
        return vicinity;
    }

    public String getIcon() {
        return icon;
    }

    public float getRating() {
        return rating;
    }

    public String getStatus() {
        return status;
    }

    public String getDescription() {
        return "No description available";
    }

    public Bitmap getBitmap(){
        String url = this.getIcon();
        Bitmap icon = null;
        try {
            InputStream in = new java.net.URL(url).openStream();
            icon = BitmapFactory.decodeStream(in);
        } catch (Exception e) {
            Log.e("Error", e.getMessage());
            e.printStackTrace();
        }
        return icon;
    }
}
