package com.example.natallialemiasheuskaya.userretrofit.presentation.screens;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.natallialemiasheuskaya.userretrofit.R;
import com.example.natallialemiasheuskaya.userretrofit.presentation.base.BaseMVVMActivity;
import com.example.natallialemiasheuskaya.userretrofit.databinding.ActiviryUserBinding;

public class UserActivity extends BaseMVVMActivity<ActiviryUserBinding,UserViewModel,UserRouter> {


    @Override
    public int provideLayoutId() {
        return R.layout.activiry_user;
    }

    @Override
    public UserViewModel provideViewModel() {
        UserViewModel  userViewModel = ViewModelProviders.of(this).get(UserViewModel.class);
        userViewModel.userId = getIntent().getStringExtra("ID");
        Log.e("Main","id2"+userViewModel.userId);
        return userViewModel;
    }

    @Override
    public UserRouter provideRouter() {
        return new UserRouter(this);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }


}
