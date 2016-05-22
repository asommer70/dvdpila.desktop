package com.thehoick.dvdpila;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

public class PilaGenerator {
    private static Settings mSettings = new Settings();

    private static Retrofit.Builder mBuilder = new Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create());

//    Retrofit retrofit = mBuilder.build();

    PilaGenerator() {
//        mBuilder.build();
//        PilaService pilaService = PilaService.retrofit.create(PilaService.class);
//        Call<Dvds> call = pilaService.getDvds(options);
    }

    Retrofit.Builder getBuilder() {
        mBuilder.baseUrl(mSettings.getSettings().getProperty("url"));
        return mBuilder;
//        mBuilder.build();
    }

//    public static <S> S createService(Class<S> serviceClass) {
//        Retrofit adapter = mBuilder.build();
//        return adapter.create(serviceClass);
//    }

    static void changeBaseUrl() {
        System.out.println("url: " + mSettings.getSettings().getProperty("url"));
        mBuilder = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create());

    }
}
