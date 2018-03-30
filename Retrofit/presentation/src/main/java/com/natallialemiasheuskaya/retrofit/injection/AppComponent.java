package com.natallialemiasheuskaya.retrofit.injection;




import com.natallialemiasheuskaya.retrofit.EditViewModel;
import com.natallialemiasheuskaya.retrofit.UserViewModel;
import com.natallialemiasheuskaya.retrofit.UsersViewModel;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class}) //компонент между даггером и классами вызывающими даггер
public interface AppComponent {

    void inject(UsersViewModel usersViewModel);
    void inject(UserViewModel userViewModel);
    void inject(EditViewModel editViewModel);

}
