package com.github.hazelhunt.weather.api;

import com.github.hazelhunt.weather.model.WeatherInfo;

import retrofit.http.GET;
import retrofit.http.Query;
import rx.Observable;

public interface WeatherAPI {

    String BASE_URL = "http://api.openweathermap.org/data/2.5/weather";
    String API_KEY = "0b6af847479c2f603401ccddd46c24b0";

    String UNITS_METRIC = "metric";
    String UNITS_IMPERAL = "imperial";

    @GET("weather?")
    Observable<WeatherInfo> getCurrentWeather(@Query("lat") double lat,
                                              @Query("lon") double lon,
                                              @Query("units") String units,
                                              @Query("appid") String appid);




}