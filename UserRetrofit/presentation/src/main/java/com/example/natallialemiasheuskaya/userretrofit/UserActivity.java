package com.example.natallialemiasheuskaya.userretrofit;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;

import com.example.natallialemiasheuskaya.userretrofit.base.BaseMVVMActivity;
import com.example.natallialemiasheuskaya.userretrofit.databinding.ActiviryUserBinding;

public class UserActivity extends BaseMVVMActivity<ActiviryUserBinding,UserViewModel> {


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
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }


}
