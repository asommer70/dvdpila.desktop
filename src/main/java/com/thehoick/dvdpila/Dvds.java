package com.thehoick.dvdpila;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Dvds {

    @SerializedName("count")
    @Expose
    private Integer count;
    @SerializedName("dvds")
    @Expose
    private List<Dvd> dvds = new ArrayList<Dvd>();
    @Expose
    private Integer maxPages;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<Dvd> getDvds() {
        return dvds;
    }

    void setDvds(List<Dvd> dvds) {
        this.dvds = dvds;
    }

    public Integer getMaxPages() {
        maxPages = this.getCount() / 10;
        return maxPages;
    }

}