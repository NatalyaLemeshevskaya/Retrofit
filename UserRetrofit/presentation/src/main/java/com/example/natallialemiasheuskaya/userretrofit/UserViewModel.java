package com.example.natallialemiasheuskaya.userretrofit;

import android.content.Context;
import android.content.Intent;
import android.databinding.BindingAdapter;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.domain.entity.UserEntity;
import com.example.domain.interactors.AddUserUserCase;
import com.example.domain.interactors.GetUserByIdUseCase;
import com.example.domain.interactors.RemoveUserUseCase;
import com.example.domain.interactors.SaveUserListUseCase;
import com.example.natallialemiasheuskaya.userretrofit.app.App;
import com.example.natallialemiasheuskaya.userretrofit.base.BaseViewModel;

import javax.inject.Inject;

import io.reactivex.CompletableObserver;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;

public class UserViewModel extends BaseViewModel<UserRouter>{

    @Override
    public void createInject() {
        App.getAppComponent().inject(UserViewModel.this);
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

    public String userId;
    public ObservableField<String> userName = new ObservableField<>("");
    public ObservableField<String> userAge = new ObservableField<>("");
    public ObservableField<String> userUrl = new ObservableField<>("");
    public ObservableBoolean isVisible = new ObservableBoolean(false) ;

    @Override
    public void onResume() {
        super.onResume();
        getUser();
    }

    public void getUser(){

        getUserByIdUseCase.get(userId).toObservable().subscribe(new Observer<UserEntity>() {
            @Override
            public void onSubscribe(Disposable d) {
                compositeDisposable.add(d);
            }

            @Override
            public void onNext(UserEntity userEntity) {
                userName.set(userEntity.getUserName());
                Log.e("Main","name"+userName);
                userAge.set(String.valueOf(userEntity.getAge()));
                userUrl.set(userEntity.getProfileUrl());

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

    public void onClickEditUser(){
        isVisible.set(true);
    }

    public void onClickSaveUser(){
        saveUserListUseCase.save(new UserEntity
                (userName.get(),Integer.valueOf(userAge.get()),userUrl.get(),userId)).subscribe(new CompletableObserver() {
            @Override
            public void onSubscribe(Disposable d) {
                compositeDisposable.add(d);
            }

            @Override
            public void onComplete() {
                isVisible.set(false);
            }

            @Override
            public void onError(Throwable e) {
                Log.e("eee","error"+e.toString());

            }
        });

    }

    public void onClickAddUser(){

        addUserUserCase.addUser(new UserEntity(userName.get(),Integer.valueOf(userAge.get()),userUrl.get(),""))
                .subscribe(new CompletableObserver() {
            @Override
            public void onSubscribe(Disposable d) {
                compositeDisposable.add(d);
            }

            @Override
            public void onComplete() {
                isVisible.set(false);
                Intent intent = new Intent(context, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }

            @Override
            public void onError(Throwable e) {
                Log.e("eee","error"+e.toString());

            }
        });

    }

    public void onClickRemoveUser(){

        removeUserUseCase.remove(userId).subscribe(new Action() {
            @Override
            public void run() throws Exception {
                Intent intent = new Intent(context, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);

            }
        });
    }


    @BindingAdapter({"src"})
    public static void loadImage(ImageView view, String url) {
        Glide.with(view.getContext()).load(url).into(view);
    }

}
