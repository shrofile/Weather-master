package com.example.sonal.weather.model;


import com.google.gson.annotations.SerializedName;

public class City {
    @SerializedName("name")
    public String name;

    @SerializedName("country")
    public String country;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
