package com.example.sonal.weather.api;



import com.example.sonal.weather.adapter.WeatherAdapter;

import javax.inject.Singleton;

import dagger.Component;

@Component(modules = {NetModule.class})
@Singleton
public interface ApplicationComponent {

    void inject(WeatherAdapter weatherAdapter);

}
