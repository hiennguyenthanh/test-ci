package com.example.hiennguyen.rxjava;

import io.realm.RealmObject;

/**
 * Created by hiennguyen on 16/02/2017
 */

public class RealmUser extends RealmObject {
    private String login;

    public RealmUser() {
    }

    public RealmUser(String login) {
        this.login = login;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
}
