package com.example.natallialemiasheuskaya.userretrofit.mvp.base;

public interface BaseView {

    void showProgress();
    void dismissProgress();
    void showError(Exception e);

}
