package com.natallialemiasheuskaya.retrofit;

import android.content.Context;
import android.content.Intent;
import android.databinding.ObservableField;

import com.natallialemiasheuskaya.domain.entity.UserEntity;
import com.natallialemiasheuskaya.domain.interactors.GetUserUseCase;
import com.natallialemiasheuskaya.retrofit.app.App;
import com.natallialemiasheuskaya.retrofit.base.BaseViewModel;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;


public class UsersViewModel extends BaseViewModel {


    public UsersAdapter usersAdapter = new UsersAdapter();
    public ObservableField<List<UserEntity>> list = new ObservableField<>();


    public Context context;

    @Inject
    public GetUserUseCase getUserUseCase;


    @Override
    public void createInject() {
        App.getAppComponent().inject(this);
    }


    public UsersViewModel(){

        super();

        getUserUseCase.get().subscribe(new Observer<List<UserEntity>>() {
            @Override
            public void onSubscribe(Disposable d) {
                compositeDisposable.add(d);
            }

            @Override
            public void onNext(List<UserEntity> userEntities) {
                usersAdapter.setList(userEntities);
                usersAdapter.setListener(new UsersAdapter.OnClickUserListener() {
                    @Override
                    public void onClick(UserEntity userEntity) {
                        Intent intent = new Intent(context,UserActivity.class);
                        intent.putExtra("id",userEntity.getId());
                        context.startActivity(intent);
                    }
                });

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });


    }

}
