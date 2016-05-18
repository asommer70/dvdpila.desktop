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

//        System.out.println("Getting DVDs...");
//        DvdsService service = retrofit.create(DvdsService.class);
//        mDvds = service.getDvds();
//
        System.out.printf("mDvds.size: %d %n", mDvds.size());
        for (Dvd dvd : mDvds) {
            System.out.println(dvd);
        }
    }

}
