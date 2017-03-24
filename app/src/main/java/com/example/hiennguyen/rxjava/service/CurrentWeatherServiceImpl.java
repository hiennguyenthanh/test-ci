package com.example.hiennguyen.rxjava.service;

import com.example.hiennguyen.rxjava.model.WeatherData;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.ReplaySubject;

/**
 * Created by hiennguyen on 14/03/2017
 */

public class CurrentWeatherServiceImpl implements CurrentWeatherService {
    private static final String TAG = CurrentWeatherServiceImpl.class.getSimpleName();

    private String API = "ac356b517a6d4b0ea5272246171403";
    private static CurrentWeatherServiceImpl mInstance;
    private WeatherService mService;
//    private OnResponseListener mListener;
    private ReplaySubject<WeatherData> dataReplaySubject;

//    public CurrentWeatherServiceImpl(OnResponseListener listener) {
//        this.mListener = listener;
//        mService = WeatherService.retrofit.create(WeatherService.class);
//    }
//
//    public static CurrentWeatherServiceImpl getInstance(OnResponseListener listener) {
//        if (mInstance == null) {
//            mInstance = new CurrentWeatherServiceImpl(listener);
//        }
//        mInstance.mListener = listener;
//        return mInstance;
//    }

    public CurrentWeatherServiceImpl() {
        mService = WeatherService.retrofit.create(WeatherService.class);
    }

    public static CurrentWeatherServiceImpl getInstance() {
        if (mInstance == null) {
            mInstance = new CurrentWeatherServiceImpl();
        }
        return mInstance;
    }

    @Override
    public void getCurrentWeather(String city, Observer<WeatherData> observer) {
        Observable<WeatherData> observable = mService.getWeatherData(API, city);
        observable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);

//                .subscribe(new Observer<WeatherData>() {
//                    @Override
//                    public void onSubscribe(Disposable d) {
//
//                    }
//
//                    @Override
//                    public void onNext(WeatherData value) {
//                        mListener.onSuccess(value);
//                        Log.e(TAG, "onNext: " + value.getCurrent().getTempC());
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        mListener.onFailure(e.getMessage());
//                    }
//
//                    @Override
//                    public void onComplete() {
//
//                    }
//                });

    }
}
