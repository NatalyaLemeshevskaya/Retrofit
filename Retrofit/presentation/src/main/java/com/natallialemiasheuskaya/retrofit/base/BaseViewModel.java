package com.natallialemiasheuskaya.retrofit.base;


import android.arch.lifecycle.ViewModel;

import io.reactivex.disposables.CompositeDisposable;

public abstract class BaseViewModel extends ViewModel {

    protected CompositeDisposable compositeDisposable = new CompositeDisposable();

    public BaseViewModel() {
        super();
        createInject();

    }

    public abstract void createInject();



    public void onResume(){

    }

    public void onStart(){

    }
    public void onPause(){

    }
    public void onStop(){

    }

    @Override
    protected void onCleared() {
        super.onCleared();
        if(compositeDisposable.isDisposed()) {
            compositeDisposable.dispose();
        }
    }
}
