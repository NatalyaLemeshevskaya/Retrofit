package com.example.natallialemiasheuskaya.userretrofit.mvp;

import com.example.natallialemiasheuskaya.userretrofit.mvp.base.BasePresenter;

public abstract class UserPresenter extends BasePresenter<UserView,UserRouter>{

    public abstract void onUserClick();
}
