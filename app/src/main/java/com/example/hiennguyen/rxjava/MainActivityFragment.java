package com.example.hiennguyen.rxjava;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.observables.ConnectableObservable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.AsyncSubject;
import io.reactivex.subjects.BehaviorSubject;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.ReplaySubject;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    private static final String TAG = MainActivityFragment.class.getSimpleName();

    public MainActivityFragment() {
        List<String> names = new ArrayList<>();
        names.add("in");
        names.add("taro");
        names.add("Hello");
        names.add("Android");
        names.add("Stream");

        Flowable.just("Hello world!!").subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                Log.e(TAG, "accept: " + s);
            }
        });

        Observable<List<String>> observable = Observable.just(names);
        Observable<String> observable1 = Observable.just("hien");

        Observable.range(0, 5)
                .map(x -> Integer.toBinaryString(x*x))
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String value) {
                        Log.e(TAG, "onNext: " + value);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "onError: " + e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        Log.e(TAG, "onComplete");
                    }
                });

        Observable observable2 = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                try {
                    for (String s : names) {
                        if (e.isDisposed())
                            return;
                        e.onNext(s);
                    }
                    e.onComplete();
                } catch (Exception ex) {
                    e.onError(ex);
                }

            }
        });

        exampleFrom(names);

        Single<List<String>> listSingle = getString(names);
        showListString(listSingle);

        observableWithMainThread(names);
    }
    //return value of 2 subscribe is different
    public void coldObservable() throws InterruptedException {
        Observable<Long> cold = Observable.interval(200, TimeUnit.MILLISECONDS);
        cold.subscribe(l -> Log.e(TAG, "First: " + l));
        Thread.sleep(500);
        cold.subscribe(l -> Log.e(TAG, "Second: " + l));
    }

    //return value of 2 subscribe is like
    public void hotObservable() throws InterruptedException {
        ConnectableObservable<Long> hot = Observable.interval(200, TimeUnit.MILLISECONDS).publish();
        hot.connect();
        hot.subscribe(l -> Log.e(TAG, "First: " + l));
        Thread.sleep(500);
        hot.subscribe(l -> Log.e(TAG, "Second: " + l));
    }

    //only emmit last item when Observable had completed
    public void asyncSubject() {
        AsyncSubject<String> asyncSubject = AsyncSubject.create();
        asyncSubject.onNext("One");
        asyncSubject.onNext("Two");
        asyncSubject.onNext("Three");
        asyncSubject.onComplete();

        asyncSubject.subscribe(x -> Log.e(TAG, "asyncSubject: " + x));
    }

    //only emmit most recent item it and drop item default and item previous => hot observable
    //http://reactivex.io/RxJava/javadoc/rx/subjects/BehaviorSubject.html
    public void behaviorSubject() {
        BehaviorSubject<String> behaviorSubject = BehaviorSubject.create();
        behaviorSubject.onNext("One");
        behaviorSubject.onNext("Two");
        behaviorSubject.onNext("Three");
        behaviorSubject.subscribe(x -> Log.e(TAG, "behaviorSubject: " + x));
        //create after subscribe
        behaviorSubject.onNext("Four");
        behaviorSubject.onNext("Fine");
        behaviorSubject.onNext("Six");
    }

    //only emmit all value when subscribe is call before call onNext
    public void publishSubject() {
        PublishSubject<String> publishSubject = PublishSubject.create();
        publishSubject.subscribe(x -> Log.e(TAG, "publishSubject1: " + x));
        publishSubject.onNext("One");
        publishSubject.onNext("Two");
        publishSubject.onNext("Three");
        publishSubject.subscribe(x -> Log.e(TAG, "publishSubject2: " + x));
        publishSubject.onNext("Four");
//        publishSubject.onComplete();
    }


    //get all value from onNext
    public void replaySubject() {
        ReplaySubject<String> replaySubject = ReplaySubject.create();
        replaySubject.onNext("One");
        replaySubject.onNext("Two");
        replaySubject.onNext("Three");
        replaySubject.onComplete();
        replaySubject.subscribe(x -> Log.e(TAG, "replaySubject: " + x));
    }


    //example using Observable.just()
    public void exampleJust(List<String> list) {
        Observable<List<String>> observable = Observable.just(list);
        observable.subscribe(x -> Log.e(TAG, "exampleJust: " + x));
    }

    public void exampleFrom(List<String> list) {
        Observable.fromIterable(list)
                .filter(s -> s.contains("e"))
                .map(String::toUpperCase)
                .subscribe(s -> Log.e(TAG, "exampleFrom: " + s));
    }


    //Single only have 2 method onSuccess and onError
    public Single<List<String>> getString(List<String> listString) {
        return Single.create(emitter -> {
            Thread thread = new Thread(() -> {
                try {
                    List<String> list = listString;
                    emitter.onSuccess(list);
                } catch (Exception ex) {
                    emitter.onError(ex);
                }
            });
            thread.start();
        });
    }

    public void observableWithMainThread(List<String> listString) {
        Observable.fromIterable(listString)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(s -> Log.e(TAG, "observableWithMainThread: " + s));
    }

    public void showListString(Single<List<String>> listSingle) {
        listSingle.subscribeWith(new DisposableSingleObserver<List<String>>() {
            @Override
            public void onSuccess(List<String> value) {
                Log.e(TAG, "onSuccess: " + value);
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }
}
