package com.example.sonal.weather.adapter;

import com.example.sonal.weather.api.DaggerApplicationComponent;
import com.example.sonal.weather.api.WeatherAPI;
import com.example.sonal.weather.model.WeatherInfo;

import javax.inject.Inject;

import rx.Observable;

public class WeatherAdapter {

    private static WeatherAdapter instance;


    @Inject
    WeatherAPI weatherAPI;

    private WeatherAdapter() {
        DaggerApplicationComponent.builder().build().inject( this);

    }

    public static WeatherAdapter getInstance() {
        if (instance == null) {
            instance = new WeatherAdapter();
        }
        return instance;
    }

    public Observable<WeatherInfo> getCurrentWeatherByName(String city) {
        return weatherAPI.getCurrentWeatherByName(city,WeatherAPI.UNITS_METRIC,WeatherAPI.day_count,WeatherAPI.API_KEY);
    }

    public Observable<WeatherInfo> getCurrentWeatherByLocation(Double lat,Double lon) {
        return weatherAPI.getCurrentWeatherByLocation(lat,lon,WeatherAPI.UNITS_METRIC,WeatherAPI.day_count,WeatherAPI.API_KEY);
    }


}