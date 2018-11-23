package com.rushabhvakharwala.helpmev2.API;


import com.rushabhvakharwala.helpmev2.Models.PostLoginModels.PlacesRequest;
import com.rushabhvakharwala.helpmev2.Models.PostLoginModels.PlacesResponse;
import com.rushabhvakharwala.helpmev2.Models.SignUpModels.SignUpRequest;
import com.rushabhvakharwala.helpmev2.Models.SignUpModels.SignUpResponse;
import com.rushabhvakharwala.helpmev2.Models.LoginModels.UserToken;


import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Url;

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
            @Header("")
        @Body SignUpRequest signUpRequest
    );


    
    @POST("places/nearby_places/fetch")
    Call<PlacesResponse> nearbyPlacesList(
            @Body PlacesRequest places_request
    );
}