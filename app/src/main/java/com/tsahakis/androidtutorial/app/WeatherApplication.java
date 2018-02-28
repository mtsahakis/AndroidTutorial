package com.tsahakis.androidtutorial.app;


import android.app.Application;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.tsahakis.androidtutorial.api.WeatherApi;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class WeatherApplication extends Application {

    private static final String WEATHER_API_ENDPOINT = "http://api.openweathermap.org/data/2.5/";

    private WeatherApi mWeatherApi;

    @Override
    public void onCreate() {
        super.onCreate();

        createWeatherApi();
    }

    public WeatherApi getWeatherApi() {
        return mWeatherApi;
    }

    private void createWeatherApi() {
        // http logging interceptor
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        // create OkHttpClient
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(logging)
                .build();

        // Gson
        Gson gson = new GsonBuilder().create();
        GsonConverterFactory factory = GsonConverterFactory.create(gson);

        // Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(WEATHER_API_ENDPOINT)
                .client(okHttpClient)
                .addConverterFactory(factory)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        // Weather API
        mWeatherApi = retrofit.create(WeatherApi.class);
    }
}
