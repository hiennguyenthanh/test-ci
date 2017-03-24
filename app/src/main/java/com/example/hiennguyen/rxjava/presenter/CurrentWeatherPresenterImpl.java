package com.example.hiennguyen.rxjava.presenter;

import com.example.hiennguyen.rxjava.CurrentView;
import com.example.hiennguyen.rxjava.interactor.CurrentWeatherInteractor;
import com.example.hiennguyen.rxjava.interactor.CurrentWeatherInteractorImpl;
import com.example.hiennguyen.rxjava.model.WeatherData;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by hiennguyen on 15/03/2017
 */

public class CurrentWeatherPresenterImpl implements CurrentWeatherPresenter {
    private static CurrentWeatherPresenterImpl mInstance;
    private CurrentView mView;
    private CurrentWeatherInteractor mInteractor;

    public CurrentWeatherPresenterImpl(CurrentView mView) {
        this.mView = mView;
        mInteractor = CurrentWeatherInteractorImpl.getInstance();
    }

    public static CurrentWeatherPresenterImpl getInstance(CurrentView view) {
        if (mInstance == null) {
            mInstance = new CurrentWeatherPresenterImpl(view);
        }
        mInstance.mView = view;
        return mInstance;
    }

    @Override
    public void getCurrentWeather(String city) {
        mView.showProgress();
        mInteractor.getCurrentWeather(city, new Observer<WeatherData>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(WeatherData value) {
                mView.getData(value);
            }

            @Override
            public void onError(Throwable e) {
                mView.hideProgress();
                mView.getError(e.toString());
            }

            @Override
            public void onComplete() {
                mView.hideProgress();
            }
        });
    }

//    @Override
//    public void onSuccess(WeatherData data) {
//        mView.getData(data);
//    }
//
//    @Override
//    public void onFailure(String error) {
//        mView.getError(error);
//    }
}
