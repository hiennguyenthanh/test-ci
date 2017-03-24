package com.example.hiennguyen.rxjava.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by hiennguyen on 14/03/2017
 */

public class Current {
    @SerializedName("temp_c")
    private double tempC;

    @SerializedName("temp_f")
    private double tempF;

    private Condition condition;

    public Current() {
    }

    public double getTempC() {
        return tempC;
    }

    public void setTempC(double tempC) {
        this.tempC = tempC;
    }

    public double getTempF() {
        return tempF;
    }

    public void setTempF(double tempF) {
        this.tempF = tempF;
    }

    public Condition getCondition() {
        return condition;
    }

    public void setCondition(Condition condition) {
        this.condition = condition;
    }
}
