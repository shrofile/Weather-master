package com.example.sonal.weather.presenter;

import com.example.sonal.weather.adapter.WeatherAdapter;
import com.example.sonal.weather.model.WeatherInfo;
import com.example.sonal.weather.view.WeatherView;
import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class WeatherPresenter extends MvpBasePresenter<WeatherView> {

    public void doObtainWeatherByName(String cityName) {
        if (getView() == null) {
            return;
        }

        getView().showLoading();

        WeatherAdapter.getInstance().getCurrentWeatherByName(cityName)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<WeatherInfo>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        getView().showError(e.getMessage());
                    }

                    @Override
                    public void onNext(WeatherInfo weather) {
                        getView().onWeatherObtained(weather);
                    }
                });
    }


    public void doObtainWeatherByLocation(Double lat,Double lon) {
        if (getView() == null) {
            return;
        }

        getView().showLoading();

        WeatherAdapter.getInstance().getCurrentWeatherByLocation(lat,lon)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<WeatherInfo>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        getView().showError(e.getMessage());
                    }

                    @Override
                    public void onNext(WeatherInfo weather) {
                        getView().onWeatherObtained(weather);
                    }
                });
    }

}