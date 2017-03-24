package com.example.hiennguyen.rxjava.interactor;

import com.example.hiennguyen.rxjava.model.WeatherData;
import com.example.hiennguyen.rxjava.service.CurrentWeatherService;
import com.example.hiennguyen.rxjava.service.CurrentWeatherServiceImpl;

import io.reactivex.Observer;

/**
 * Created by hiennguyen on 15/03/2017
 */

public class CurrentWeatherInteractorImpl implements CurrentWeatherInteractor {

    private static CurrentWeatherInteractorImpl mInstance;
    private CurrentWeatherService mService;

//    public CurrentWeatherInteractorImpl(OnResponseListener mListener) {
//        this.mService = CurrentWeatherServiceImpl.getInstance(mListener);
//    }
//
//    public static CurrentWeatherInteractorImpl getInstance(OnResponseListener listener) {
//        if (mInstance == null) {
//            mInstance = new CurrentWeatherInteractorImpl(listener);
//        }
//        return mInstance;
//    }

    public CurrentWeatherInteractorImpl() {
        this.mService = CurrentWeatherServiceImpl.getInstance();
    }

    public static CurrentWeatherInteractorImpl getInstance() {
        if (mInstance == null) {
            mInstance = new CurrentWeatherInteractorImpl();
        }
        return mInstance;
    }

    @Override
    public void getCurrentWeather(String city, Observer<WeatherData> observer) {
        mService.getCurrentWeather(city, observer);
    }
}
