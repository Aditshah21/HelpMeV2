package com.rushabhvakharwala.helpmev2.API;

import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import com.google.gson.*;

public class RetrofitClient {
    private  static final String BASE_URL = "https://helpme-dev.herokuapp.com/";
    public static RetrofitClient mInstance;
    private Retrofit retrofit;
    private Retrofit retrofitGoogleGson;
    private RetrofitClient(){
        Gson gson = new GsonBuilder() .setLenient() .create();

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        retrofitGoogleGson = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().setLenient().create()))
                .build();
    }

    public static synchronized RetrofitClient getInstance(){
        if (mInstance==null){
            mInstance = new RetrofitClient();
        }
        return mInstance;
    }

    public API getApi(){
        return retrofit.create(API.class);
    }
    public API getGoogleGsonApi(){ return  retrofitGoogleGson.create(API.class); }
}

