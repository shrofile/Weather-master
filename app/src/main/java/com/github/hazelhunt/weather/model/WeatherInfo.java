package com.github.hazelhunt.weather.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class WeatherInfo {

    @SerializedName("list")
    @Expose
    private List<Weather> weather = new ArrayList<Weather>();

    @SerializedName("city")
    private City city;

    public List<Weather> getWeather() {
        return weather;
    }

    public void setWeather(List<Weather> weather) {
        this.weather = weather;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }
}