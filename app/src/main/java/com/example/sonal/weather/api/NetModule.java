package com.example.sonal.weather.api;


import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;


@Module
public class NetModule {
    private static Retrofit retrofit = null;


    @Provides
    @Singleton
    Retrofit provideRetrofit() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(WeatherAPI.BASE_URL)
                    .build();
        }
        return retrofit;
    }


    @Provides
    @Singleton
    WeatherAPI provideApiServie(Retrofit retrofit) {
        return retrofit.create(WeatherAPI.class);
    }







}
