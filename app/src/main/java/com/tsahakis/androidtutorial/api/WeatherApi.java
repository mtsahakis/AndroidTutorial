package com.tsahakis.androidtutorial.api;


import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface WeatherApi {

    @Headers({
            "Accept: application/json",
            "Content-type: application/json"
    })
    @GET("weather")
    Single<WeatherResponse> getWeatherItem(@Query("q") String city,
                                           @Query("appid") String appid,
                                           @Query("units") String units);

}
