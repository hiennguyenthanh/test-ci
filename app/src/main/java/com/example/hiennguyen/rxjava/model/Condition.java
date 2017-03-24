package com.example.hiennguyen.rxjava.model;

/**
 * Created by hiennguyen on 14/03/2017
 */

public class Condition {
    private String text;
    private String icon;
    private int code;

    public Condition() {
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
