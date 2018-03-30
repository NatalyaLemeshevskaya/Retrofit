package com.natallialemiasheuskaya.retrofit.app;

import android.app.Application;

import com.natallialemiasheuskaya.retrofit.injection.AppComponent;
import com.natallialemiasheuskaya.retrofit.injection.AppModule;


//MultyDeks - если супер большое приложение
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
