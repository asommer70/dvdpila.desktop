package com.thehoick.dvdpila;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

import java.util.List;
import java.util.Map;
import java.util.Properties;

public interface DvdsService {
    Properties mSettings = Settings.getSettings();

    public static final String URL = mSettings.getProperty("url");

    // http://localhost:3000/dvds.json?page=2
    @GET("dvds.json")
    Call<Dvds> getDvds(@QueryMap Map<String, String> options);

    @GET("dvds/{id}.json")
    Call<Dvd> getDvdData(@Path("id") String id);

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
}
