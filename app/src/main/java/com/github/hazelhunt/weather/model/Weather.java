package com.github.hazelhunt.weather.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Weather {

    @SerializedName("dt")
    @Expose
    private Long date_mili_sec;

    @SerializedName("main")
    @Expose
    private MainWeather mainWeather;

    @SerializedName("weather")
    @Expose
    private List<Weather_overview> weather_overview;

    @SerializedName("clouds")
    @Expose
    private Clouds clouds;


    @SerializedName("wind")
    private Wind wind;


    public Long getDate_mili_sec() {
        return date_mili_sec;
    }

    public List<Weather_overview> getWeather_overview() {
        return weather_overview;
    }

    public void setWeather_overview(List<Weather_overview> weather_overview) {
        this.weather_overview = weather_overview;
    }

    public void setDate_mili_sec(Long date_mili_sec) {
        this.date_mili_sec = date_mili_sec;
    }

    public MainWeather getMainWeather() {
        return mainWeather;
    }

    public void setMainWeather(MainWeather mainWeather) {
        this.mainWeather = mainWeather;
    }



    public Clouds getClouds() {
        return clouds;
    }

    public void setClouds(Clouds clouds) {
        this.clouds = clouds;
    }

    public Wind getWind() {
        return wind;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }
}