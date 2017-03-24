package com.example.hiennguyen.rxjava.interactor;

import com.example.hiennguyen.rxjava.model.WeatherData;

import io.reactivex.Observer;

/**
 * Created by hiennguyen on 14/03/2017
 */

public interface CurrentWeatherInteractor {
    void getCurrentWeather(String city, Observer<WeatherData> observer);
}
