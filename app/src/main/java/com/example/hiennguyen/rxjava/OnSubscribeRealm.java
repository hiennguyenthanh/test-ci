package com.example.hiennguyen.rxjava;

import android.content.Context;

import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.Observable;
import io.realm.Realm;
import io.realm.RealmObject;

/**
 * Created by hiennguyen on 16/02/2017
 */

public abstract class OnSubscribeRealm<T extends RealmObject> implements FlowableOnSubscribe<T> {
    private Context context;
    private String fileName;

    public OnSubscribeRealm(Context context) {
        this.context = context;
        this.fileName = null;
    }

    public OnSubscribeRealm(Context context, String fileName) {
        this.context = context;
        this.fileName = fileName;
    }

    @Override
    public void subscribe(FlowableEmitter<T> e) throws Exception {
        Realm realm = Realm.getDefaultInstance();

    }
}
