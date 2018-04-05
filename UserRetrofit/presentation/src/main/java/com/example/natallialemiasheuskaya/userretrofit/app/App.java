package com.example.natallialemiasheuskaya.userretrofit.app;

import android.app.Application;

import com.example.natallialemiasheuskaya.userretrofit.injection.AppComponent;
import com.example.natallialemiasheuskaya.userretrofit.injection.AppModule;
import com.example.natallialemiasheuskaya.userretrofit.injection.DaggerAppComponent;


public class App extends Application {

    private static AppComponent appComponent;

    public static AppComponent getAppComponent() {
        return appComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();



    }

}
