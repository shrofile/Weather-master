package com.github.hazelhunt.weather.model;


import com.google.gson.annotations.SerializedName;

public class MainWeather {
    @SerializedName("temp")
    String temp;

    @SerializedName("temp_min")
    String temp_min;

    @SerializedName("temp_max")
    String temp_max;

    @SerializedName("pressure")
    String pressure;

    @SerializedName("humidity")
    String humidity;


    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public String getTemp_min() {
        return temp_min;
    }

    public void setTemp_min(String temp_min) {
        this.temp_min = temp_min;
    }

    public String getTemp_max() {
        return temp_max;
    }

    public void setTemp_max(String temp_max) {
        this.temp_max = temp_max;
    }

    public String getPressure() {
        return pressure;
    }

    public void setPressure(String pressure) {
        this.pressure = pressure;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }
}
