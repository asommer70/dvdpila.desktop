package com.thehoick.dvdpila;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

public class PilaBuilder {
    private static Settings mSettings = new Settings();

    private static Retrofit.Builder mBuilder = new Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create());

    PilaBuilder() {
    }

    Retrofit.Builder getBuilder() {
        mBuilder.baseUrl(mSettings.getSettings().getProperty("url"));
        return mBuilder;
    }
}
