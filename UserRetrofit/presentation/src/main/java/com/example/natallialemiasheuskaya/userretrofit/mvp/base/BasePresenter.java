package com.example.natallialemiasheuskaya.userretrofit.mvp.base;


import android.support.annotation.Nullable;

import com.example.natallialemiasheuskaya.userretrofit.base.Router;

import io.reactivex.disposables.CompositeDisposable;

public abstract class BasePresenter<View extends BaseView, R extends Router> {

    @Nullable
    private R router;

    @Nullable
    public View view;

    protected CompositeDisposable compositeDisposable = new CompositeDisposable();

    public BasePresenter() {
        super();
        createInject();

    }

    public abstract void createInject();


    public void attach(View view,R router){
        this.router = router;
        this.view = view;
    }

    public void detach(){
        router = null;
        view = null;

    }

    public void onResume(){

   }

    public void onStart(){

   }
    public void onPause(){

    }
    public void onStop(){

    }

    public void onDestroy(){
        if(compositeDisposable.isDisposed()) {
            compositeDisposable.dispose();
        }
    }

}
