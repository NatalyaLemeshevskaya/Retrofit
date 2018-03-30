package com.natallialemiasheuskaya.retrofit;

import android.content.Context;
import android.content.Intent;
import android.databinding.ObservableField;
import android.view.View;

import com.natallialemiasheuskaya.domain.entity.UserEntity;
import com.natallialemiasheuskaya.domain.interactors.SaveUserListUseCase;
import com.natallialemiasheuskaya.retrofit.app.App;
import com.natallialemiasheuskaya.retrofit.base.BaseViewModel;

import javax.inject.Inject;

import io.reactivex.functions.Action;

/**
 * Created by natallialemiasheuskaya on 30.03.2018.
 */

public class EditViewModel extends BaseViewModel {


    @Override
    public void createInject() {
        App.getAppComponent().inject(this);
    }

    @Inject
    public SaveUserListUseCase saveUserListUseCase;


    public ObservableField<String> userName = new ObservableField<>("");
    public ObservableField<String> userAge = new ObservableField<>("");
    public ObservableField<String> userUrl = new ObservableField<>("");
    public ObservableField<String> userId = new ObservableField<>("");


    public void OnClickButtonSave(View view){

        saveUserListUseCase.save(new UserEntity(userName.get(),Integer.valueOf(userAge.get()),userId.get(),userUrl.get()))
                .subscribe(new Action() {
                    @Override
                    public void run() throws Exception {
                        Intent intent  = new Intent(view.getContext(),UserActivity.class);
                        view.getContext().startActivity(intent);
                    }
                });
    }


}
