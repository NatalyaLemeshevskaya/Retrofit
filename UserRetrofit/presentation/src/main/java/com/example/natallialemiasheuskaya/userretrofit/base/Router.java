package com.example.natallialemiasheuskaya.userretrofit.base;

import android.app.Activity;

public abstract class Router {


    private Activity activity;

    public Router(Activity activity) {
        this.activity = activity;
    }

    public Activity getActivity() {
        return activity;
    }
}
