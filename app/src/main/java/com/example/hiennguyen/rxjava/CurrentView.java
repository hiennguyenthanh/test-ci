package com.example.hiennguyen.rxjava;

import com.example.hiennguyen.rxjava.model.WeatherData;

/**
 * Created by hiennguyen on 15/03/2017
 */

public interface CurrentView {
    void getData(WeatherData data);
    void getError(String error);

    void showProgress();
    void hideProgress();
}
