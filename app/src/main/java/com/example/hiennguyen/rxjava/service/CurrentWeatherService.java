package com.example.hiennguyen.rxjava.service;

import com.example.hiennguyen.rxjava.model.WeatherData;

import io.reactivex.Observer;

/**
 * Created by hiennguyen on 14/03/2017
 */

public interface CurrentWeatherService {
    void getCurrentWeather(String city, Observer<WeatherData> observer);
}
