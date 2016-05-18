package com.thehoick.dvdpila;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

import java.util.List;

public interface DvdsService {
    public static final String URL = "http://localhost:3000/";

    @GET("dvds.json")
    Call<List<Dvd>> getDvds();

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
}
