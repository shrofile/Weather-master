package com.github.hazelhunt.weather.adapter;

import com.github.hazelhunt.weather.api.WeatherAPI;
import com.github.hazelhunt.weather.model.WeatherInfo;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;
import rx.Observable;

public class WeatherAdapter {

    private static WeatherAdapter instance;

    private WeatherAPI weatherAPI;

    private WeatherAdapter() {
        Retrofit retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(WeatherAPI.BASE_URL)
                .build();

        weatherAPI = retrofit.create(WeatherAPI.class);
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