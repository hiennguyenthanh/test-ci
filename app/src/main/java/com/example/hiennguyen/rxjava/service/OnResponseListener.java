package com.example.hiennguyen.rxjava.service;

import com.example.hiennguyen.rxjava.model.WeatherData;

/**
 * Created by hiennguyen on 14/03/2017
 */

public interface OnResponseListener {
    void onSuccess(WeatherData data);

    void onFailure(String error);
}
