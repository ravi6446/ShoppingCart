package com.ravi.practiceapp.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ravi.practiceapp.interfaces.IAPIService;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ravi on 01/10/17.
 */

public class DataFetcher {


    public static IAPIService getRetroInterface(){

    Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
            .create();

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://mobiletest-hackathon.herokuapp.com")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build();

        return retrofit.create(IAPIService.class);
    }
}
