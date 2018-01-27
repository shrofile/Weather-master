package com.github.hazelhunt.weather;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.github.hazelhunt.weather.adapter.WhetherAdapter;
import com.github.hazelhunt.weather.model.Weather;
import com.github.hazelhunt.weather.model.WeatherInfo;
import com.github.hazelhunt.weather.presenter.WeatherPresenter;
import com.github.hazelhunt.weather.view.WeatherView;
import com.hannesdorfmann.mosby.mvp.MvpActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity
        extends MvpActivity<WeatherView, WeatherPresenter>
        implements WeatherView {

    @Bind(R.id.root_layout)
    ViewGroup rootLayout;

    @Bind(R.id.weather_recycler)
    RecyclerView weatherRecyclerView;

    @Bind(R.id.search_edt)
    EditText search_edt;

    private ProgressDialog progressDialog;
    private GPSTracker gps;

    private static final int MY_PERMISSIONS_REQUEST_LOCATION = 111;

    @TargetApi(Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(getString(R.string.please_wait));

        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        gps = new GPSTracker(this);
        onRefreshButtonClick();

        if (!(Build.VERSION.SDK_INT < Build.VERSION_CODES.M))
            checkSelfPermission();

        if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)){
            Toast.makeText(this, "GPS is Enabled in your devide", Toast.LENGTH_SHORT).show();
        }else{
            showGPSDisabledAlertToUser();
        }


        search_edt.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    performSearch(v.getText().toString());
                    return true;
                }
                return false;
            }
        });

    }

    private void performSearch(String cityName) {
        if (cityName!=null) {
            presenter.doObtainWeatherByName(cityName);
        }
    }

    @TargetApi(Build.VERSION_CODES.M)
    private void checkSelfPermission() {
        if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    MY_PERMISSIONS_REQUEST_LOCATION);

            // MY_PERMISSIONS_REQUEST_LOCATION is an
            // app-defined int constant

            return;
        }
    }

    private void showGPSDisabledAlertToUser(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("GPS is disabled in your device. Would you like to enable it?")
                .setCancelable(false)
                .setPositiveButton("Goto Settings Page To Enable GPS",
                        new DialogInterface.OnClickListener(){
                            public void onClick(DialogInterface dialog, int id){
                                Intent callGPSSettingIntent = new Intent(
                                        android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                                startActivity(callGPSSettingIntent);

                                checkSelfPermission();

                                Toast.makeText(MainActivity.this, "Permission needs to be granted", Toast.LENGTH_SHORT).show();
                            }
                        });
        alertDialogBuilder.setNegativeButton("Cancel",
                new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog, int id){
                        dialog.cancel();
                        Toast.makeText(MainActivity.this, "Permission needs to be granted", Toast.LENGTH_SHORT).show();
                    }
                });
        AlertDialog alert = alertDialogBuilder.create();
        alert.show();
    }

    @OnClick(R.id.refresh_button)
    public void onRefreshButtonClick() {
        if (gps.canGetLocation()) {
            presenter.doObtainWeatherByLocation(gps.getLatitude(), gps.getLongitude());
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        ButterKnife.unbind(this);
    }

    @NonNull
    @Override
    public WeatherPresenter createPresenter() {
        return new WeatherPresenter();
    }

    @Override
    public void showLoading() {
        progressDialog.show();
    }

    @Override
    public void hideLoading() {
        progressDialog.hide();
    }

    @Override
    public void showError(String error) {
        hideLoading();
        Snackbar.make(rootLayout, error, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void onWeatherObtained(WeatherInfo w) {
        hideLoading();
        showWeather(w);
    }

    private void showWeather(WeatherInfo w) {

        List<Weather> weatherInfos = new ArrayList<>();
        weatherInfos.addAll(w.getWeather());
        WhetherAdapter whetherAdapter = new WhetherAdapter(weatherInfos, this);
        weatherRecyclerView.setAdapter(whetherAdapter);
        weatherRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }


    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_LOCATION: {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! do the
                    // calendar task you need to do.

                    Toast.makeText(this, "Permission is granted", Toast.LENGTH_SHORT).show();

                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                    Toast.makeText(this, "Permission needs to be granted", Toast.LENGTH_SHORT).show();
                    checkSelfPermission();
                }
                return;
            }

            // other 'switch' lines to check for other
            // permissions this app might request
        }
    }


}