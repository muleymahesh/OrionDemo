package com.example.a1084081.oriondemoapp.model;

import java.io.Serializable;

/**
 * Created by 1084081 on 5/1/17.
 */

public class Address implements Serializable{
    private String street;
    private String suite;
    private String city;
    private String zipcode;
    private Geo geo;


    public Address() {}

    public Address(
            String street,
            String suite,
            String city,
            String zipcode,
            Geo geo) {

        this.street = street;
        this.suite = suite;
        this.city = city;
        this.zipcode = zipcode;
        this.geo = geo;
    }


    public String getStreet() {
        return street;
    }

    public String getSuite() {
        return suite;
    }

    public String getCity() {
        return city;
    }

    public String getZipcode() {
        return zipcode;
    }

    public Geo getGeo() {
        return geo;
    }




    public void setStreet(String street) {
        this.street = street;
    }

    public void setSuite(String suite) {
        this.suite = suite;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public void setGeo(Geo geo) {
        this.geo = geo;
    }
}
