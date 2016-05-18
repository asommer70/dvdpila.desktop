package com.thehoick.dvdpila;


import retrofit2.Call;
import retrofit2.Retrofit;

import java.io.IOException;
import java.util.List;

public class Controller {
    List<Dvd> mDvds;

    public void getDvds() {
        DvdsService dvdsService = DvdsService.retrofit.create(DvdsService.class);
        Call<List<Dvd>> call = dvdsService.getDvds();

        try {
            mDvds = call.execute().body();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.printf("mDvds.size: %d %n", mDvds.size());
//        System.out.println(mDvds.get(0).getTitle());

        for (Dvd dvd : mDvds) {
            System.out.println(dvd);
        }
    }

}
