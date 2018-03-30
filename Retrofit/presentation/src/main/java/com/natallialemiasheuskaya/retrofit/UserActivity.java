package com.natallialemiasheuskaya.retrofit;


import android.os.Bundle;

import com.natallialemiasheuskaya.retrofit.base.BaseMVVMActivity;
import com.natallialemiasheuskaya.retrofit.base.BaseViewModel;

public class UserActivity extends BaseMVVMActivity {

    @Override
    public int provideLayoutId() {
        return R.layout.activity_user;
    }

    @Override
    public BaseViewModel provideViewModel() {
        return new UserViewModel();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
