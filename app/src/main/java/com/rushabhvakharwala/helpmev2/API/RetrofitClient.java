package com.rushabhvakharwala.helpmev2.API;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private  static final String BASE_URL = "https://helpme-dev.herokuapp.com/";
    public static RetrofitClient mInstance;
    private Retrofit retrofit;
    private RetrofitClient(){
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
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
}

