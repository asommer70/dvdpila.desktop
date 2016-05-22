package com.thehoick.dvdpila;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

import java.util.Map;

public interface PilaService {
    Settings mSettings = new Settings();

    @GET("dvds.json")
    Call<Dvds> getDvds(@QueryMap Map<String, String> options);

    @GET("dvds/{id}.json")
    Call<Dvd> getDvdData(@Path("id") String id);
}
