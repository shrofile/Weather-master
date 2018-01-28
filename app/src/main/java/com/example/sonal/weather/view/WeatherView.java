package com.example.sonal.weather.view;

import com.example.sonal.weather.model.WeatherInfo;
import com.hannesdorfmann.mosby.mvp.MvpView;

public interface WeatherView extends MvpView {

    void showLoading();
    void hideLoading();

    void showError(String error);

    void onWeatherObtained(WeatherInfo w);
}