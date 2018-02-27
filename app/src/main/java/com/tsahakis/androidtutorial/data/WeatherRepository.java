package com.tsahakis.androidtutorial.data;


import com.tsahakis.androidtutorial.BuildConfig;

import io.reactivex.Single;

public class WeatherRepository {

    private static final String WEATHER_LOCATION = "London,uk";
    private static final String UNITS = "metric";

    private final WeatherApi mWeatherApi;

    public WeatherRepository(WeatherApi weatherApi) {
        mWeatherApi = weatherApi;
    }

    public Single<WeatherResponse> getWeatherItem() {
        return mWeatherApi.getWeatherItem(
                WEATHER_LOCATION,
                BuildConfig.OPEN_WEATHER_MAP_API_KEY,
                UNITS
        );
    }

}
