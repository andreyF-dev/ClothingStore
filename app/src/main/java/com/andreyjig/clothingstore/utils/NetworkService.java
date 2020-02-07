package com.andreyjig.clothingstore.utils;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkService {

    private static NetworkService instance;
    public static String url = "https://sequeniatesttask.s3-eu-west-1.amazonaws.com";
    public static String idUrl = "https://sequeniatesttask.s3-eu-west-1.amazonaws.com/products/%d.json";
    private Retrofit retrofit;

    private NetworkService(){
        retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static NetworkService newInstance(){
        if (instance == null){
            return new NetworkService();
        }
        return instance;
    }

    public JSONPlaceHolderApi getJSONApi(){
        return retrofit.create(JSONPlaceHolderApi.class);
    }
}