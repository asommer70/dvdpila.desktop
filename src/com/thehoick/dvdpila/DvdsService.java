package com.thehoick.dvdpila;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

import java.util.List;
import java.util.Properties;

public interface DvdsService {
    Properties mSettings = Settings.getSettings();

    public static final String URL = mSettings.getProperty("url");

    @GET("dvds.json")
    Call<List<Dvd>> getDvds();

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
}
