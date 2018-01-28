package com.example.sonal.weather.api;

import com.example.sonal.weather.model.WeatherInfo;

import retrofit.http.GET;
import retrofit.http.Query;
import rx.Observable;

public interface WeatherAPI {

    String BASE_URL = "http://api.openweathermap.org/data/2.5/";
    String API_KEY = "0b6af847479c2f603401ccddd46c24b0";
    String UNITS_METRIC = "metric";
    String UNITS_IMPERAL = "imperial";
    Integer day_count = 5;

    @GET("forecast?")
    Observable<WeatherInfo> getCurrentWeatherByName(@Query("q") String city,
                                                    @Query("units") String units,
                                                    @Query("cnt") Integer count,
                                                    @Query("appid") String appid);

    @GET("forecast?")
    Observable<WeatherInfo> getCurrentWeatherByLocation(@Query("lat") Double lat,
                                                        @Query("lon") Double lon,
                                                        @Query("units") String units,
                                                        @Query("cnt") Integer count,
                                                        @Query("appid") String appid);


}