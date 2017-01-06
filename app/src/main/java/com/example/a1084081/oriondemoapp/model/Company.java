package com.example.a1084081.oriondemoapp.model;

import java.io.Serializable;

/**
 * Created by 1084081 on 5/1/17.
 */

public class Company implements Serializable{
    private String name;
    private String catchPhrase;
    private String bs;


    public Company() {}

    public Company(
            String name,
            String catchPhrase,
            String bs) {

        this.name = name;
        this.catchPhrase = catchPhrase;
        this.bs = bs;
    }


    public String getName() {
        return name;
    }

    public String getCatchPhrase() {
        return catchPhrase;
    }

    public String getBs() {
        return bs;
    }




    public void setName(String name) {
        this.name = name;
    }

    public void setCatchPhrase(String catchPhrase) {
        this.catchPhrase = catchPhrase;
    }

    public void setBs(String bs) {
        this.bs = bs;
    }
}
