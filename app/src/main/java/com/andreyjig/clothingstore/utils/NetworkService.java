package com.andreyjig.clothingstore.utils;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkService {

    private static NetworkService instance;
    private static String url = "https://sequeniatesttask.s3-eu-west-1.amazonaws.com";
    private Retrofit retrofit;

    private NetworkService(){
        retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static NetworkService getInstance(){
        if (instance == null){
            instance = new NetworkService();
        }
        return instance;
    }

    public ProductJSON getJSONApi(){
        return retrofit.create(ProductJSON.class);
    }
}
