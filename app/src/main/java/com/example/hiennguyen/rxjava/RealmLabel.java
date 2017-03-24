package com.example.hiennguyen.rxjava;

import io.realm.RealmObject;

/**
 * Created by hiennguyen on 16/02/2017
 */

public class RealmLabel extends RealmObject {
    private String name;
    private String color;

    public RealmLabel() {
    }

    public RealmLabel(String name, String color) {
        this.name = name;
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
