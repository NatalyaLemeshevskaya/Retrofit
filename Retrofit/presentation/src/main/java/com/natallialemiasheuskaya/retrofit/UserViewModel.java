package com.natallialemiasheuskaya.retrofit;

import android.content.Intent;
import android.databinding.ObservableField;
import android.view.View;

import com.natallialemiasheuskaya.domain.entity.UserEntity;
import com.natallialemiasheuskaya.domain.interactors.GetUserByIdUseCase;
import com.natallialemiasheuskaya.domain.interactors.RemoveUserUseCase;
import com.natallialemiasheuskaya.retrofit.app.App;
import com.natallialemiasheuskaya.retrofit.base.BaseViewModel;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;


public class UserViewModel extends BaseViewModel {
    @Override
    public void createInject() {
        App.getAppComponent().inject(this);
    }

    @Inject
    public GetUserByIdUseCase getUserByIdUseCase;

    @Inject
    public RemoveUserUseCase removeUserUseCase;



    public String userId = "";
    public ObservableField<String> userName = new ObservableField<>("");
    public ObservableField<String> userAge = new ObservableField<>("");
    public ObservableField<String> userUrl = new ObservableField<>("");

    public UserViewModel() {

        super();

        getUserByIdUseCase.get(userId).subscribe(new Observer<UserEntity>() {
            @Override
            public void onSubscribe(Disposable d) {
                compositeDisposable.add(d);
            }

            @Override
            public void onNext(UserEntity userEntity) {

                userName.set(userEntity.getUserName());
                userAge.set(String.valueOf(userEntity.getAge()));
                userUrl.set(userEntity.getProfileUrl());

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });

    }

    public void onRemoveButtonClick(View view){

        removeUserUseCase.remove(userId).subscribe(new Action() {
            @Override
            public void run() throws Exception {
                Intent intent = new Intent(view.getContext(),UsersActivity.class);
                view.getContext().startActivity(intent);
            }
        });
    }

    public void onEditButtonClick(View view){


    }
}
