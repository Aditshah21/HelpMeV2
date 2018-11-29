package com.rushabhvakharwala.helpmev2.Models.PostLoginModels;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.InputStream;
import java.net.URL;

public class NearbyPlaces {

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("vicinity")
    @Expose
    private String vicinity;

    @SerializedName("icon")
    @Expose
    private String icon;

    @SerializedName("rating")
    @Expose
    private String rating;

    @SerializedName("photo")
    @Expose
    private String photo;
    @Expose
    @SerializedName("status")
    private String status;

    public NearbyPlaces(String name, String vicinity, String icon, String rating, String photo, String status) {
        this.name = name;
        this.vicinity = vicinity;
        this.icon = icon;
        this.rating = rating;
        this.photo = photo;
        this.status = status;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setVicinity(String vicinity) {
        this.vicinity = vicinity;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public void setStatus(String status) {
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

    public String getRating() {
        return rating;
    }

    public String getPhoto(){ return photo; }

    public String getStatus() {
        return status;
    }

    public String getDescription() {
        return "No description available";
    }


}
