package com.example.hiennguyen.rxjava;

import io.realm.RealmList;

/**
 * Created by hiennguyen on 16/02/2017
 */

public class RealmIssue {
    private String title;
    private String body;
    private RealmUser user;
    private RealmList<RealmLabel> labels;

    public RealmIssue() {
    }

    public RealmIssue(String title, String body, RealmUser user, RealmList<RealmLabel> labels) {
        this.title = title;
        this.body = body;
        this.user = user;
        this.labels = labels;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public RealmUser getUser() {
        return user;
    }

    public void setUser(RealmUser user) {
        this.user = user;
    }

    public RealmList<RealmLabel> getLabels() {
        return labels;
    }

    public void setLabels(RealmList<RealmLabel> labels) {
        this.labels = labels;
    }
}
