package com.example.a1084081.oriondemoapp.model;

import java.io.Serializable;

/**
 * Created by 1084081 on 5/1/17.
 */

public class Geo implements Serializable{
    private String lat;
    private String lng;
    public Geo(
            String lat,
            String lng) {

        this.lat = lat;
        this.lng = lng;
    }


    public String getLat() {
        return lat;
    }

    public String getLng() {
        return lng;
    }




    public void setLat(String lat) {
        this.lat = lat;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }
}
