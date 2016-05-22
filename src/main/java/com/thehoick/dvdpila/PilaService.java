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

public interface PilaService {
//    public static String URL =  Settings.getSettings().getProperty("url");
    Settings mSettings = new Settings();

    @GET("dvds.json")
    Call<Dvds> getDvds(@QueryMap Map<String, String> options);

    @GET("dvds/{id}.json")
    Call<Dvd> getDvdData(@Path("id") String id);

    PilaGenerator g = new PilaGenerator();

//    Retrofit retrofit = new Retrofit.Builder()
//            .baseUrl(mSettings.getSettings().getProperty("url"))
//            .addConverterFactory(GsonConverterFactory.create())
//            .build();
}
