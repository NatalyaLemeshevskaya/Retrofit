package com.example.natallialemiasheuskaya.userretrofit.injection;


import com.example.natallialemiasheuskaya.userretrofit.MainViewModel;
import com.example.natallialemiasheuskaya.userretrofit.UserViewModel;
import com.example.natallialemiasheuskaya.userretrofit.mvp.SigninUserPresenter;


import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class}) //компонент между даггером и классами вызывающими даггер
public interface AppComponent {

    void inject(MainViewModel mainViewModel);
    void inject(UserViewModel userViewModel);
    void inject(SigninUserPresenter userPresenter);

}
