package com.example.androidretrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkService {
    private static NetworkService mInstance;
    //private static final String BASE_URL = "https://10.0.0.2:56789";
    private static final String BASE_URL = "https://jsonplaceholder.typicode.com";

    private Retrofit mRetrofit;

    private NetworkService() {
        mRetrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
    public static NetworkService getInstance() {
        if (mInstance == null) {
            mInstance = new NetworkService();
        }
        return mInstance;
    }
    public FastDostavka getJSONApi() {
        return mRetrofit.create(FastDostavka.class);
    }
}