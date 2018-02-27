package com.tsahakis.androidtutorial.data;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WeatherResponse {

    @SerializedName("main")
    @Expose
    private WeatherItem mWeatherItem;

    public WeatherItem getWeatherItem() {
        return mWeatherItem;
    }

    public void setWeatherItem(WeatherItem weatherItem) {
        mWeatherItem = weatherItem;
    }
}
