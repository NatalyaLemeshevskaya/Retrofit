package com.example.natallialemiasheuskaya.userretrofit.mvp;

import android.content.Context;
import android.util.Log;

import com.example.domain.entity.UserEntity;
import com.example.domain.interactors.AddUserUserCase;
import com.example.domain.interactors.GetUserByIdUseCase;
import com.example.domain.interactors.RemoveUserUseCase;
import com.example.domain.interactors.SaveUserListUseCase;
import com.example.natallialemiasheuskaya.userretrofit.app.App;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class SigninUserPresenter extends UserPresenter {

    @Override
    public void createInject() {
        App.getAppComponent().inject(this);
    }


    @Inject
    public Context context;

    @Inject
    public SaveUserListUseCase saveUserListUseCase;

    @Inject
    public GetUserByIdUseCase getUserByIdUseCase;

    @Inject
    public AddUserUserCase addUserUserCase;

    @Inject
    public RemoveUserUseCase removeUserUseCase;

    public SigninUserPresenter() {

        super();



        getUserByIdUseCase.get("id").toObservable().subscribe(new Observer<UserEntity>() {
            @Override
            public void onSubscribe(Disposable d) {
                compositeDisposable.add(d);
            }

            @Override
            public void onNext(UserEntity userEntity) {

//                userName.set(userEntity.getUserName());
//                Log.e("Main","name"+userName);
//                userAge.set(String.valueOf(userEntity.getAge()));
//                userUrl.set(userEntity.getProfileUrl());

                view.showUser(userEntity);

            }

            @Override
            public void onError(Throwable e) {

                Log.e("Main","error"+e.toString());
            }

            @Override
            public void onComplete() {

            }
        });
    }

    @Override
    public void onUserClick() {

    }
}
