package com.example.natallialemiasheuskaya.userretrofit;

import android.databinding.ObservableField;

import android.util.Log;

import com.example.domain.entity.UserEntity;
import com.example.domain.interactors.GetUserUseCase;
import com.example.natallialemiasheuskaya.userretrofit.app.App;
import com.example.natallialemiasheuskaya.userretrofit.base.BaseViewModel;


import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class MainViewModel extends BaseViewModel {

   // public ItemAdapter2 itemAdapter2 = new ItemAdapter2();

    public ItemAdapter itemAdapter = new ItemAdapter();
    public ObservableField<List<UserEntity>> list = new ObservableField<>();



    @Override
    public void createInject() {

        App.getAppComponent().inject(MainViewModel.this);
    }


    @Inject
    public GetUserUseCase getUserUseCase;

    public MainViewModel() {

        super();

        getUserUseCase.get().toObservable().subscribe(new Observer<List<UserEntity>>() {
            @Override
            public void onSubscribe(Disposable d) {
                compositeDisposable.add(d);
            }

            @Override
            public void onNext(List<UserEntity> userEntities) {
                Log.e("onNext","onNext"+userEntities.size());
                itemAdapter.setImageList(userEntities);
                //itemAdapter2.setItems(userEntities);


            }

            @Override
            public void onError(Throwable e) {

                Log.e("onNext","error"+e.toString());
            }

            @Override
            public void onComplete() {

            }
        });
    }



}
