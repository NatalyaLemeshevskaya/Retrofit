package com.example.natallialemiasheuskaya.userretrofit.presentation.screens;

import android.databinding.ObservableField;

import android.util.Log;

import com.example.data.entity.Error;
import com.example.domain.entity.UserEntity;
import com.example.domain.interactors.GetUserUseCase;
import com.example.natallialemiasheuskaya.userretrofit.ItemAdapter;
import com.example.natallialemiasheuskaya.userretrofit.app.App;
import com.example.natallialemiasheuskaya.userretrofit.presentation.base.BaseViewModel;


import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class MainViewModel extends BaseViewModel<MainRouter> {

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
        //if(router!= null) router.navigateToUser("df");

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
                itemAdapter.setListener(new ItemAdapter.OnUserClickListener() {
                    @Override
                    public void onUserClick(String id) {
                        if(router!= null){
                            router.navigateToUser(id);
                        }
                    }
                });


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
