package com.rushabhvakharwala.helpmev2.API;


import com.rushabhvakharwala.helpmev2.Models.PostLoginModels.NearbyPlaces;
import com.rushabhvakharwala.helpmev2.Models.PostLoginModels.PlacesRequest;
import com.rushabhvakharwala.helpmev2.Models.PostLoginModels.PlacesResponse;
import com.rushabhvakharwala.helpmev2.Models.SignUpModels.SignUpRequest;
import com.rushabhvakharwala.helpmev2.Models.SignUpModels.SignUpResponse;
import com.rushabhvakharwala.helpmev2.Models.LoginModels.UserToken;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;


public interface API {
    @FormUrlEncoded
    @POST("oauth/token")
    Call<UserToken> signInUser (
        @Field("grant_type") String grant_type,
        @Field("email") String email,
        @Field("password") String password
    );

    @POST("users")
    Call<SignUpResponse> signUpUser(
        @Body SignUpRequest signUpRequest
    );



//    @POST("places/nearby_places/fetch")
//    Call<PlacesResponse> nearbyPlacesList(
//            @Body PlacesRequest places_request
//    );

//    @POST("places/nearby_places/fetch")
//    Call<ArrayList<NearbyPlaces>> nearbyPlacesList(
//      @Body PlacesRequest placesRequest
//    );

    @POST("places/nearby_places/fetch")
    @Headers({"Content-Type: application/json"})
    Call<HashMap<String, List<HashMap<String, String>>>> nearby_places(
            @Body HashMap<String, HashMap<String, HashMap<String, String>>> body
    );
}
