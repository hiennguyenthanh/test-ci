package com.example.hiennguyen.rxjava.service;

import com.example.hiennguyen.rxjava.model.WeatherData;

import io.reactivex.Observable;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by hiennguyen on 14/03/2017
 */

public interface WeatherService {
    Retrofit retrofit = new Retrofit.Builder()
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("http://api.apixu.com/v1/")
            .build();

    @GET("current.json")
    Observable<WeatherData> getWeatherData(@Query("key") String key, @Query("q") String city);
}
