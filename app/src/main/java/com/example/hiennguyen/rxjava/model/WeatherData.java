package com.example.hiennguyen.rxjava.model;

/**
 * Created by hiennguyen on 14/03/2017
 */

public class WeatherData {
    private Location location;
    private Current current;
    private int humidity;
    private int cloud;

    public WeatherData() {
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Current getCurrent() {
        return current;
    }

    public void setCurrent(Current current) {
        this.current = current;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public int getCloud() {
        return cloud;
    }

    public void setCloud(int cloud) {
        this.cloud = cloud;
    }
}
